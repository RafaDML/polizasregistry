package com.polizas.polizasregistry.polizas.data.network

import com.polizas.polizasregistry.core.network.models.ResponseModel
import com.polizas.polizasregistry.polizas.data.network.response.ObtenerPolizasResponse
import retrofit2.Response
import javax.inject.Inject

class PolizasService @Inject constructor(
    private val polizasClient: PolizasClient,
) {
    suspend fun obtenerPolizas(/*request*/): Response<ResponseModel<ObtenerPolizasResponse>> {
        val valor =  polizasClient.obtenerPolizas()
        return valor
    }
    suspend fun eliminarPoliza(idPoliza: Int?): Response<ResponseModel<String>> {

        val valor =  polizasClient.eliminarPolizas(idPoliza)
        return valor
    }
}