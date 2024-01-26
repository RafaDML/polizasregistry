package com.polizas.polizasregistry.components.modals.create.data.network

import com.polizas.polizasregistry.components.modals.create.data.network.request.EditPolizaRequest
import com.polizas.polizasregistry.components.modals.create.data.network.request.NewPolizaRequest
import com.polizas.polizasregistry.components.modals.create.data.network.response.NewPolizaResponse
import com.polizas.polizasregistry.core.network.models.ResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface InputPolizaClient {
    @POST("polizas")
    suspend fun addNewPoliza(@Body request: NewPolizaRequest): Response<ResponseModel<NewPolizaResponse>>

    @PUT("polizas")
    suspend fun editPoliza(@Body request: EditPolizaRequest): Response<ResponseModel<Boolean>>
}