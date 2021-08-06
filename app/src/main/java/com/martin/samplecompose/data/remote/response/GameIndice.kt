package com.martin.catchemall.data.remote.response


import com.google.gson.annotations.SerializedName
import com.martin.samplecompose.data.remote.response.Version

data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int,
    @SerializedName("version")
    val version: Version
)