package com.example.polizasregistry.Polizas.data.network

import com.coppel.pvm.core.network.models.ResponseModel
import com.example.polizasregistry.Polizas.data.network.response.ObtenerPolizasResponse
import retrofit2.Response
import javax.inject.Inject

class PolizasService @Inject constructor(
    private val polizasClient: PolizasClient,
) {
    suspend fun obtenerPolizas(/*request*/): Response<ResponseModel<ObtenerPolizasResponse>> {
        return polizasClient.obtenerPolizas()
    }
}