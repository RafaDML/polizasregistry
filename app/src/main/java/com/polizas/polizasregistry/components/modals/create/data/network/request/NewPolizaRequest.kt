package com.polizas.polizasregistry.components.modals.create.data.network.request

import com.google.gson.annotations.SerializedName

data class NewPolizaRequest(
    var empleado : Int?    = null,
    var sku      : Int?    = null,
    var cantidad : Int?    = null,
    var fecha    : String? = null
)
