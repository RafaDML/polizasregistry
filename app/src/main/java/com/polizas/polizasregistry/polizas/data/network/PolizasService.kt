package com.polizas.polizasregistry.polizas.data.network

import android.util.Log
import com.polizas.polizasregistry.core.network.models.ResponseModel
import com.polizas.polizasregistry.polizas.data.network.response.ObtenerPolizasResponse
import retrofit2.Response
import javax.inject.Inject

class PolizasService @Inject constructor(
    private val polizasClient: PolizasClient,
) {
    suspend fun obtenerPolizas(/*request*/): Response<ResponseModel<ObtenerPolizasResponse>> {
        val valor =  polizasClient.obtenerPolizas()
        Log.i("SERVICE " ,"RETURN VALOR ${valor.body()}")
        return valor
    }
}