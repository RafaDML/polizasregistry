package com.polizas.polizasregistry.components.modals.empleados.data.repositories

import android.util.Log
import com.polizas.polizasregistry.components.modals.empleados.data.network.EmpleadoService
import com.polizas.polizasregistry.components.modals.empleados.data.network.response.ObtenerEmpleadosResponse
import com.polizas.polizasregistry.components.modals.empleados.domain.model.ObtenerEmpleadosItem
import com.polizas.polizasregistry.components.modals.empleados.domain.model.toDomain
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.network.models.ResponseModel
import retrofit2.Response
import javax.inject.Inject

class EmpleadoRepository @Inject constructor(
    private val api: EmpleadoService
) {
    suspend fun obtenerEmpleados(): BaseModel<ObtenerEmpleadosItem> {
        try {
            val response = api.obtenerEmpleados()
            return getResponseObtenerEmpleados(response)
        } catch (ex: Exception) {
            return BaseModel.Error("Error de conexion")
        }
    }

    private fun getResponseObtenerEmpleados(response: Response<ResponseModel<ObtenerEmpleadosResponse>>): BaseModel<ObtenerEmpleadosItem> {
        if (response.code() == 200) {
            val obtenerEmpleadosResponse = response.body()?.data?.toDomain()
            Log.i("OBTENER Empleados REPOSITORY", "VALOR $obtenerEmpleadosResponse")
            val meta = response.body()?.meta
            return if (meta?.status == "ERROR") {
                BaseModel.Error(msg = response.message())
            } else {
                return BaseModel.Success(obtenerEmpleadosResponse)
            }
        } else if (response.code() == 403) {
            return BaseModel.Error(msg = "Se cerrará la sesión", true)
        } else {
            val mensaje =
                if (response.message() != null) response.message() else " Error al obtener Empleados"
            return BaseModel.Error(msg = mensaje)
        }
    }
}