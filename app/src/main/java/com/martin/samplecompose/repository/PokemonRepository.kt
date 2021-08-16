package com.martin.samplecompose.repository

import com.martin.catchemall.data.remote.response.Pokemon
import com.martin.samplecompose.data.remote.response.PokemonList
import com.martin.samplecompose.data.remote.PokeApi
import com.martin.samplecompose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class PokemonRepository @Inject constructor(private val api: PokeApi) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit = limit, offset = offset)
        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
        return Resource.Success(response)
    }


    suspend fun getPokemonInfo(name: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(name = name)
        } catch (e: Exception) {
            return Resource.Error(e.toString())
        }
        return Resource.Success(response)
    }
}