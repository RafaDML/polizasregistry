package com.polizas.polizasregistry.core.network.models

import com.google.gson.annotations.SerializedName
data class MetaModel(

    @SerializedName("status") var status: String? = null,
    @SerializedName("type") var type: Int? = 0

)