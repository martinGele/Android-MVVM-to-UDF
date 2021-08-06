package com.martin.samplecompose.data.remote.response


import com.google.gson.annotations.SerializedName
import com.martin.samplecompose.data.remote.response.TypeX

data class Type(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeX
)