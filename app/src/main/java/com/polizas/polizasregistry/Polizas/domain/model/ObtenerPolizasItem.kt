package com.polizas.polizasregistry.polizas.domain.model

import com.polizas.polizasregistry.polizas.data.network.response.ObtenerPolizasResponse as ObtenerPolizasResponse
import com.polizas.polizasregistry.polizas.data.network.response.Polizas as PolizasResponse
import com.polizas.polizasregistry.polizas.data.network.response.Empleado as EmpleadoResponse
import com.polizas.polizasregistry.polizas.data.network.response.Inventario as InventarioResponse

data class ObtenerPolizasItem(
    var polizas: ArrayList<Polizas> = arrayListOf()
)

data class Polizas(

    var idPoliza: Int? = null,
    var empleado: Empleado? = null,
    var inventario: Inventario? = null,
    var cantidad: Int? = null,
    var fecha: String? = null
)

data class Empleado(
    var idEmpleado: Int? = null,
    var nomEmpleado: String? = null,
    var apEmpleado: String? = null,
    var puesto: String? = null
)

data class Inventario(
    var sku: Int? = null,
    var nombre: String? = null,
    var cantidad: Int? = null,
)


fun ObtenerPolizasResponse.toDomain() = ObtenerPolizasItem(
    polizas = polizas.map { it.toDomain() } as ArrayList<Polizas>
)

fun PolizasResponse.toDomain() = Polizas(

    idPoliza = idPoliza,
    empleado = empleado?.toDomain(),
    inventario = inventario?.toDomain(),
    cantidad = cantidad,
    fecha = fecha

)

fun EmpleadoResponse.toDomain() = Empleado(
    idEmpleado = idEmpleado,
    nomEmpleado = nomEmpleado,
    apEmpleado = apEmpleado,
    puesto = puesto
)

fun InventarioResponse.toDomain() = Inventario(
    sku = sku,
    nombre = nombre,
    cantidad = cantidad
)