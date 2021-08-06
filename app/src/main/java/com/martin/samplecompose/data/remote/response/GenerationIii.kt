package com.martin.catchemall.data.remote.response


import com.google.gson.annotations.SerializedName
import com.martin.samplecompose.data.remote.response.Emerald

data class GenerationIii(
    @SerializedName("emerald")
    val emerald: Emerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen,
    @SerializedName("ruby-sapphire")
    val rubySapphire: RubySapphire
)