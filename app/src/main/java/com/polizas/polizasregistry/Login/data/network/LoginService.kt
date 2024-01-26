package com.polizas.polizasregistry.login.data.network

import com.polizas.polizasregistry.login.data.network.request.LoginRequest
import com.polizas.polizasregistry.login.data.network.response.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginClient: LoginClient,
) {
    suspend fun doLogin(loginRequest: LoginRequest): Response<LoginResponse> {
        return loginClient.doLogin(loginRequest)
    }
}