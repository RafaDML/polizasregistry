package com.polizas.polizasregistry.components.modals.empleados.domain.model

import com.polizas.polizasregistry.components.modals.empleados.data.network.response.ObtenerEmpleadosResponse as ObtenerEmpleadosResponse
import com.polizas.polizasregistry.components.modals.empleados.data.network.response.Empleados as EmpleadoResponse


data class ObtenerEmpleadosItem(
    var empleados: ArrayList<Empleados> = arrayListOf()
)

data class Empleados(

    var idEmpleado: Int? = null,
    var nomEmpleado: String? = null,
    var apEmpleado: String? = null,
    var puesto: String? = null

)

fun ObtenerEmpleadosResponse.toDomain() = ObtenerEmpleadosItem(
    empleados = empleados.map { it.toDomain() } as ArrayList<Empleados>

)

fun EmpleadoResponse.toDomain() = Empleados(
    idEmpleado = idEmpleado,
    nomEmpleado = nomEmpleado,
    apEmpleado = apEmpleado,
    puesto = puesto,

    )