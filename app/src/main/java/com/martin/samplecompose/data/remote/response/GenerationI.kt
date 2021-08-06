package com.martin.catchemall.data.remote.response


import com.google.gson.annotations.SerializedName
import com.martin.samplecompose.data.remote.response.Yellow

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: RedBlue,
    @SerializedName("yellow")
    val yellow: Yellow
)