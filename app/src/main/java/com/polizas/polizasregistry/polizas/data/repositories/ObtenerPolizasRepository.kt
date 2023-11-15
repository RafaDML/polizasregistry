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
    private val api : PolizasService
) {

    suspend fun obtenerPolizas(
    ): BaseModel<ObtenerPolizasItem> {

        val response = api.obtenerPolizas()
        Log.i("OBTENER POLIZA REPOSITORY","response    $response")

        return getResponseObtenerPolizas(response)
    }

    private fun getResponseObtenerPolizas(response: Response<ResponseModel<ObtenerPolizasResponse>>): BaseModel<ObtenerPolizasItem> {
        if (response.code() == 200) {
            val obtenerPolizasResponse = response.body()?.data?.toDomain()
            Log.i("OBTENER POLIZA REPOSITORY","VALOR $obtenerPolizasResponse")
            val meta = response.body()?.meta
            return if (meta?.status == "ERROR") {
                BaseModel.Error(msg=response.message())
            } else {
                 return BaseModel.Success(obtenerPolizasResponse)
            }
        } else {
            return BaseModel.Error(msg = response.message())
        }
    }


}