package com.polizas.polizasregistry.components.modals.create.data.network.request

data class NewPolizaRequest(
    var empleado : Int?    = null,
    var sku      : Int?    = null,
    var cantidad : Int?    = null,
    var fecha    : String? = null
)
