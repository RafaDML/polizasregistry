package com.polizas.polizasregistry.polizas.data.network

import com.polizas.polizasregistry.core.network.models.ResponseModel
import com.polizas.polizasregistry.polizas.data.network.response.*
import com.polizas.polizasregistry.polizas.domain.model.*
import com.polizas.polizasregistry.polizas.data.network.response.ObtenerPolizasResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Path
import retrofit2.http.Query

interface PolizasClient {
    @GET("polizas")
    suspend fun obtenerPolizas(): Response<ResponseModel<ObtenerPolizasResponse>>

    @DELETE("polizas/{idPoliza}")
    suspend fun eliminarPolizas(@Path("idPoliza") idPoliza: Int?): Response<ResponseModel<String>>

}