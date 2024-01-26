package com.polizas.polizasregistry.polizas.data.repositories

import android.util.Log
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.network.models.ResponseModel
import com.polizas.polizasregistry.polizas.data.network.PolizasService
import com.polizas.polizasregistry.polizas.data.network.response.ObtenerPolizasResponse
import com.polizas.polizasregistry.polizas.domain.model.ObtenerPolizasItem
import com.polizas.polizasregistry.polizas.domain.model.toDomain
import retrofit2.Response
import javax.inject.Inject

class ObtenerPolizasRepository @Inject constructor(
    private val api: PolizasService
) {

    suspend fun obtenerPolizas(
    ): BaseModel<ObtenerPolizasItem> {
        try{
            val response = api.obtenerPolizas()
            Log.i("OBTENER POLIZA REPOSITORY", "response    $response")

            return getResponseObtenerPolizas(response)
        }
        catch (e : Exception){
            return BaseModel.Error("Error de conexion")
        }

    }

    private fun getResponseObtenerPolizas(response: Response<ResponseModel<ObtenerPolizasResponse>>): BaseModel<ObtenerPolizasItem> {
        if (response.code() == 200) {
            val obtenerPolizasResponse = response.body()?.data?.toDomain()
            Log.i("OBTENER POLIZA REPOSITORY", "VALOR $obtenerPolizasResponse")
            val meta = response.body()?.meta
            return if (meta?.status == "ERROR") {
                BaseModel.Error(msg = response.message())
            } else {
                return BaseModel.Success(obtenerPolizasResponse)
            }
        } else if (response.code() == 403) {
            return BaseModel.Error(msg = "Se cerrará la sesión", true)
        } else {
            val mensaje = if (response.message() != null) response.message() else " Error al obtener pólizas"
            return BaseModel.Error(msg = mensaje)
        }
    }

    suspend fun eliminarPoliza(idPoliza: Int?): BaseModel<String> {
        try {
            Log.i("Repository ", "repository eliminar poliza")
            val response = api.eliminarPoliza(idPoliza)

            return getEliminarPolizaResponse(response)
        }
        catch (e : Exception){
            return BaseModel.Error("Error de conexion e $e")
        }
    }

    fun getEliminarPolizaResponse(response: Response<ResponseModel<String>>): BaseModel<String> {
        return if (response.code() == 200) {
            BaseModel.Success(response.body()?.message)
        } else if (response.code() == 403) {
            BaseModel.Error("Se cerrará la sesión", true)
        } else {
            BaseModel.Error(response.message())
        }
    }
}