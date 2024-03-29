package com.polizas.polizasregistry.components.modals.inventario.data.network

import com.polizas.polizasregistry.components.modals.inventario.data.network.response.ObtenerInventarioResponse
import com.polizas.polizasregistry.core.network.models.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

fun interface InventarioClient {
    @GET("inventario")
    suspend fun obtenerInventario(): Response<ResponseModel<ObtenerInventarioResponse>>
}