package com.coppel.pvm.core.network.models

import com.google.gson.annotations.SerializedName
data class MetaModel(

    @SerializedName("id_transaction") var idTransaction: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("time_elapsed") var timeElapsed: String? = null

)