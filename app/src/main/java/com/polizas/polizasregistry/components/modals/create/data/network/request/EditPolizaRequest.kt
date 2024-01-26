package com.polizas.polizasregistry.components.modals.create.data.network.request

data class EditPolizaRequest(
    var idPoliza: Int? = null,
    var sku: Int? = null,
    var empleado: Int? = null,
    var cantidad: Int? = null,
    var fecha: String? = null
)
