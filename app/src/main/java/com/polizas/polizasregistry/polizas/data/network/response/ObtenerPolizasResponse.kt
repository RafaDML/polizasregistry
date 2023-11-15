package com.polizas.polizasregistry.polizas.data.network.response

import com.google.gson.annotations.SerializedName

data class ObtenerPolizasResponse(
    @SerializedName("polizas") var polizas: ArrayList<Polizas> = arrayListOf()
)

data class Polizas(

    @SerializedName("idPoliza") var idPoliza: Int? = null,
    @SerializedName("empleado") var empleado: Empleado? = Empleado(),
    @SerializedName("inventario") var inventario: Inventario? = Inventario(),
    @SerializedName("cantidad") var cantidad: Int? = null,
    @SerializedName("fecha") var fecha: String? = null

)

data class Inventario(

    @SerializedName("sku") var sku: Int? = null,
    @SerializedName("nombre") var nombre: String? = null,
    @SerializedName("cantidad") var cantidad: Int? = null

)

data class Empleado(

    @SerializedName("idEmpleado") var idEmpleado: Int? = null,
    @SerializedName("nomEmpleado") var nomEmpleado: String? = null,
    @SerializedName("apEmpleado") var apEmpleado: String? = null,
    @SerializedName("puesto") var puesto: String? = null

)
