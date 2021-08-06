package com.martin.catchemall.data.remote.response


import com.google.gson.annotations.SerializedName
import com.martin.samplecompose.data.remote.response.StatX

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: StatX
)