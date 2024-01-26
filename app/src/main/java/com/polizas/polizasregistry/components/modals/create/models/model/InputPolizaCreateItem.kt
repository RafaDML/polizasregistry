package com.polizas.polizasregistry.components.modals.create.models.model

import com.polizas.polizasregistry.components.modals.create.data.network.response.NewPolizaResponse as NewPolizaResponse
import com.polizas.polizasregistry.components.modals.create.data.network.response.Empleado as EmpleadoResponse
import com.polizas.polizasregistry.components.modals.create.data.network.response.Inventario as InventarioResponse


data class InputPolizaCreateItem(
    var idPoliza: Int? = null,
    var empleado: Empleado? = Empleado(),
    var inventario: Inventario? = Inventario(),
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
    var cantidad: Int? = null

)

fun NewPolizaResponse.toDomain() = InputPolizaCreateItem(
    idPoliza = idPoliza,
    empleado = empleado?.toDomain(),
    inventario = inventario?.toDomain(),
    cantidad = cantidad,
    fecha = fecha,
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
    cantidad = cantidad,
)