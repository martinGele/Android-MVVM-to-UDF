package com.martin.samplecompose.feature.detailpokemon

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.catchemall.data.remote.response.Pokemon
import com.martin.samplecompose.data.remote.PokeApi
import com.martin.samplecompose.data.remote.models.PokedexListEntry
import com.martin.samplecompose.repository.PokemonRepository
import com.martin.samplecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }

}