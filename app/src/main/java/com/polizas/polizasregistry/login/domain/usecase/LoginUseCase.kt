package com.polizas.polizasregistry.login.domain.usecase

import android.util.Log
import com.polizas.polizasregistry.core.network.models.BaseModel
import javax.inject.Inject
import com.polizas.polizasregistry.login.data.network.request.LoginRequest
import com.polizas.polizasregistry.login.data.repositories.LoginRepository
import com.polizas.polizasregistry.login.domain.model.LoginItem

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend fun doLogin(email: String, password: String) : BaseModel<LoginItem> {
        Log.i("RAFA - username ", email)
        Log.i("RAFA - password ", password)
        val request = LoginRequest(username = email, password = password)
        return repository.doLogin(request)
    }
}