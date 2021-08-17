package com.martin.samplecompose.data.remote

import com.martin.catchemall.data.remote.response.Pokemon
import com.martin.catchemall.data.remote.response.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
        const val PAGE_SIZE = 50
    }

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): Pokemon

}