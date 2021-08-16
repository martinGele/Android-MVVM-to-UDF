package com.martin.samplecompose.data.remote.response


import com.google.gson.annotations.SerializedName
import com.martin.catchemall.data.remote.response.Result

data class PokemonList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Result>
)