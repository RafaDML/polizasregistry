package com.polizas.polizasregistry.login.data.repositories


import android.util.Log
import com.polizas.polizasregistry.PolizaApplication
import com.polizas.polizasregistry.core.network.models.BaseModel
import com.polizas.polizasregistry.core.sessionmanagers.SessionTokenManager
import com.polizas.polizasregistry.login.data.network.LoginService
import com.polizas.polizasregistry.login.data.network.request.LoginRequest
import com.polizas.polizasregistry.login.data.network.response.LoginResponse
import com.polizas.polizasregistry.login.domain.model.LoginItem
import com.polizas.polizasregistry.login.domain.model.toDomain
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: LoginService
) {
    suspend fun doLogin(request: LoginRequest): BaseModel<LoginItem> {
        try {
            val response = api.doLogin(request)

            val returno = getResponse(response)
            Log.i("RAFA RESULT", response.toString())

            return returno

        } catch (e: Exception) {
            return BaseModel.Error("Error de conexión")
        }
    }

    private fun getResponse(response: Response<LoginResponse>): BaseModel<LoginItem> {
        val responseToken = response.body()
        return if (response.code() == 200) {

            SessionTokenManager.saveToken(
                PolizaApplication.getApplicationContext()!!,
                responseToken?.token!!
            )

            BaseModel.Success(responseToken?.toDomain())
        } else {
            BaseModel.Error("Falló Inicio Sesión")
        }
    }

}