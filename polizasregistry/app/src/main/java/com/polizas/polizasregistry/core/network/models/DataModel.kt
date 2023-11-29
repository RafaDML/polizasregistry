package com.polizas.polizasregistry.core.network.models

import com.google.gson.annotations.SerializedName

data class DataModel<T>(

    @SerializedName("data") var data: T?

)