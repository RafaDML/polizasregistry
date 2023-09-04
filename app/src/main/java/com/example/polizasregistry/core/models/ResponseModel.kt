package com.coppel.pvm.core.network.models

import com.google.gson.annotations.SerializedName

data class ResponseModel<T>(

    @SerializedName("data") var data: DataModel<T>?,
    @SerializedName("meta") var meta: MetaModel? = MetaModel()

)