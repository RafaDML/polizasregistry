package com.polizas.polizasregistry.polizas.data.network

import com.polizas.polizasregistry.core.network.models.ResponseModel
import com.polizas.polizasregistry.polizas.data.network.response.*
import com.polizas.polizasregistry.polizas.domain.model.*
import com.polizas.polizasregistry.polizas.data.network.response.ObtenerPolizasResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface PolizasClient {
    @GET("polizas")
    suspend fun obtenerPolizas(): Response<ResponseModel<ObtenerPolizasResponse>>

    @DELETE("polizas/{idPoliza}")
    suspend fun eliminarPolizas(@Path("idPoliza") idPoliza: Int?): Response<ResponseModel<String>>

}