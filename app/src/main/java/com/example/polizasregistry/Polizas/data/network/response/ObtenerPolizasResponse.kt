package com.example.polizasregistry.Polizas.data.network.response

import com.google.gson.annotations.SerializedName

data class ObtenerPolizasResponse(
    @SerializedName("polizas") var polizas: Polizas? = Polizas()
)
data class Polizas (

    @SerializedName("idPoliza"   ) var idPoliza   : Int?    = null,
    @SerializedName("idEmpleado" ) var idEmpleado : Int?    = null,
    @SerializedName("sku"        ) var sku        : Int?    = null,
    @SerializedName("cantidad"   ) var cantidad   : Int?    = null,
    @SerializedName("fecha"      ) var fecha      : String? = null

)

