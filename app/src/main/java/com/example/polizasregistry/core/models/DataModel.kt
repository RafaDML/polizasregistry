package com.coppel.pvm.core.network.models

import com.google.gson.annotations.SerializedName

data class DataModel<T>(

    @SerializedName("response") var response: T?

)