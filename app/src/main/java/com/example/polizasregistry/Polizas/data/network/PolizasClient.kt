package com.example.polizasregistry.Polizas.data.network

import com.coppel.pvm.core.network.models.ResponseModel
import com.example.polizasregistry.Polizas.data.network.response.*
import com.example.polizasregistry.Polizas.domain.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PolizasClient {
    @POST("polizas")
    suspend fun obtenerPolizas(): Response<ResponseModel<ObtenerPolizasResponse>>

}