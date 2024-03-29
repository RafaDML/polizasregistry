package com.polizas.polizasregistry.components.modals.inventario.data.repositories

import android.util.Log
import com.polizas.polizasregistry.components.modals.inventario.data.network.InventarioService
import com.polizas.polizasregistry.components.modals.inventario.data.network.response.ObtenerInventarioResponse
import com.polizas.polizasregistry.components.modals.inventario.domain.model.ObtenerInventarioItem
import com.polizas.polizasregistry.components.modals.inventario.domain.model.toDomain
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.network.models.ResponseModel
import retrofit2.Response
import javax.inject.Inject

class InventarioRepository @Inject constructor(private val api: InventarioService) {

    suspend fun obtenerInventario(): BaseModel<ObtenerInventarioItem> {
        try {
            val response = api.obtenerInventario()
            return getResponseObtenerInventario(response)
        } catch (e: Exception) {
            return BaseModel.Error("Error de conexion")

        }
    }

    private fun getResponseObtenerInventario(response: Response<ResponseModel<ObtenerInventarioResponse>>): BaseModel<ObtenerInventarioItem> {
        return if (response.code() == 200) {
            val obtenerInventarioResponse = response.body()?.data?.toDomain()
            Log.i("OBTENER POLIZA REPOSITORY", "VALOR $obtenerInventarioResponse")
            val meta = response.body()?.meta
            return if (meta?.status == "ERROR") {
                BaseModel.Error(msg = response.message())
            } else {
                 BaseModel.Success(obtenerInventarioResponse)
            }
        } else if (response.code() == 403) {
             BaseModel.Error(msg = "Se cerrará la sesión", true)
        } else {
            val mensaje =
                response.message()
             BaseModel.Error(msg = mensaje)
        }
    }

}