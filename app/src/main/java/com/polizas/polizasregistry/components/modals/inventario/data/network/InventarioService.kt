package com.polizas.polizasregistry.components.modals.inventario.data.network

import com.polizas.polizasregistry.components.modals.inventario.data.network.response.ObtenerInventarioResponse
import com.polizas.polizasregistry.core.network.models.ResponseModel
import retrofit2.Response
import javax.inject.Inject

class InventarioService @Inject constructor(
    private val inventarioCliente : InventarioClient
) {
    suspend fun obtenerInventario(/*request*/): Response<ResponseModel<ObtenerInventarioResponse>> {
        return inventarioCliente.obtenerInventario()
    }
}