package com.example.polizasregistry.Polizas.domain.model

import com.google.gson.annotations.SerializedName
import com.example.polizasregistry.Polizas.data.network.response.ObtenerPolizasResponse as ObtenerPolizasResponse
import com.example.polizasregistry.Polizas.data.network.response.Polizas as PolizasResponse

data class ObtenerPolizasItem(
    var polizas: Polizas? = Polizas()
)
data class Polizas (

    var idPoliza   : Int?    = null,
    var idEmpleado : Int?    = null,
    var sku        : Int?    = null,
    var cantidad   : Int?    = null,
    var fecha      : String? = null

)


fun ObtenerPolizasResponse.toDomain() = ObtenerPolizasItem(
    polizas = polizas?.toDomain()
)
fun PolizasResponse.toDomain() = Polizas (

     idPoliza = idPoliza,
     idEmpleado = idEmpleado,
     sku = sku,
     cantidad = cantidad,
     fecha = fecha,

)
