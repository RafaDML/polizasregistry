package com.polizas.polizasregistry.polizas.data.network

import com.polizas.polizasregistry.core.network.models.ResponseModel
import com.polizas.polizasregistry.polizas.data.network.response.*
import com.polizas.polizasregistry.polizas.domain.model.*
import com.polizas.polizasregistry.polizas.data.network.response.ObtenerPolizasResponse
import retrofit2.Response
import retrofit2.http.GET

interface PolizasClient {
    @GET("polizas")
    suspend fun obtenerPolizas(): Response<ResponseModel<ObtenerPolizasResponse>>

}