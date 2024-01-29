package com.polizas.polizasregistry.components.modals.empleados.data.network.response

import com.google.gson.annotations.SerializedName

data class ObtenerEmpleadosResponse(
    @SerializedName("empleados" ) var empleados : ArrayList<Empleados> = arrayListOf()

)

data class Empleados (

    @SerializedName("idEmpleado"  ) var idEmpleado  : Int?    = null,
    @SerializedName("nomEmpleado" ) var nomEmpleado : String? = null,
    @SerializedName("apEmpleado"  ) var apEmpleado  : String? = null,
    @SerializedName("puesto"      ) var puesto      : String? = null

)