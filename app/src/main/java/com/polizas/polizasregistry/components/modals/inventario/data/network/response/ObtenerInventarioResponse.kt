package com.polizas.polizasregistry.components.modals.inventario.data.network.response

import com.google.gson.annotations.SerializedName

data class ObtenerInventarioResponse(
    @SerializedName("inventario") var inventario: ArrayList<Inventario> = arrayListOf()
)

data class Inventario(

    @SerializedName("sku") var sku: Int? = null,
    @SerializedName("nombre") var nombre: String? = null,
    @SerializedName("cantidad") var cantidad: Int? = null

)