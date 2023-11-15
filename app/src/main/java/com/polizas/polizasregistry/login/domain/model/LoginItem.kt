package com.polizas.polizasregistry.login.domain.model

import com.polizas.polizasregistry.login.data.network.response.LoginResponse

data class LoginItem(
    val token : String? = null
)

fun LoginResponse.toDomain() = LoginItem(
    token
)