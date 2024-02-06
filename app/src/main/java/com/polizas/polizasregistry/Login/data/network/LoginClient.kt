package com.polizas.polizasregistry.login.data.network

import com.polizas.polizasregistry.login.data.network.request.LoginRequest
import com.polizas.polizasregistry.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

fun interface LoginClient {
    @POST("auth/login")
    suspend fun doLogin(@Body loginRequest: LoginRequest) : Response<LoginResponse>

}