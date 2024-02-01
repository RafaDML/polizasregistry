package com.polizas.polizasregistry.components.modals.inventario.domain.model

import com.polizas.polizasregistry.components.modals.inventario.data.network.response.ObtenerInventarioResponse as ObtenerInventarioResponse
import com.polizas.polizasregistry.components.modals.inventario.data.network.response.Inventario as InventarioResponse

data class ObtenerInventarioItem(
    var inventario: ArrayList<Inventario> = arrayListOf()
)

data class Inventario(
    var sku: Int? = null,
    var nombre: String? = null,
    var cantidad: Int? = null
)

fun ObtenerInventarioResponse.toDomain() = ObtenerInventarioItem(
    inventario = inventario.map { it.toDomain() } as ArrayList<Inventario>
)

fun InventarioResponse.toDomain() = Inventario(
    sku = sku,
    nombre = nombre,
    cantidad = cantidad
)