package com.polizas.polizasregistry.core.network.models

import com.google.gson.annotations.SerializedName

data class ResponseModel<T>(

    @SerializedName("data") var data: T?,
    @SerializedName("meta") var meta: MetaModel? = MetaModel()

)