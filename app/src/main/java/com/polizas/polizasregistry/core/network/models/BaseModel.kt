package com.polizas.polizasregistry.core.network.models


sealed class BaseModel<out T> {
    data class Success<out T>(val data: T? = null) : BaseModel<T>()
    data class Error(val msg: String?, val cerrarSesion: Boolean = false, val errorCode: Int = 0) : BaseModel<Nothing>()
}