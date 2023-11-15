package com.polizas.polizasregistry.login.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("token" ) var token : String? = null,
)
