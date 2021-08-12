package com.martin.samplecompose.feature.listpokemon


import androidx.lifecycle.ViewModel
import com.martin.catchemall.data.remote.response.PokemonList
import com.martin.catchemall.data.remote.response.Result
import com.martin.samplecompose.data.remote.PokeApi.Companion.PAGE_SIZE
import com.martin.samplecompose.data.remote.models.PokedexListEntry
import com.martin.samplecompose.repository.PokemonRepository
import com.martin.samplecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

private const val RAW_URL =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
private const val PNG = ".png"

@HiltViewModel
class ListPokemonViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    suspend fun loadPokemonList(): Resource<PokemonList> {
        return repository.getPokemonList(PAGE_SIZE, PAGE_SIZE)
    }

    fun dataMapIndexed(results: List<Result>): List<PokedexListEntry> {
        return results.mapIndexed { index, entry ->
            val number = if (entry.url.endsWith("/")) {
                entry.url.dropLast(1).takeLastWhile { it.isDigit() }
            } else {
                entry.url.takeLastWhile { it.isDigit() }
            }
            val url =
                "$RAW_URL${number}$PNG"
            PokedexListEntry(entry.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }, url, number.toInt())
        }

    }

}

