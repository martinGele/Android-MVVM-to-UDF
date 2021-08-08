package com.martin.samplecompose.feature.detailpokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.catchemall.data.remote.response.Pokemon
import com.martin.samplecompose.repository.PokemonRepository
import com.martin.samplecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    private var singlePokemon = MutableLiveData<Resource<Pokemon>>()
    fun getSinglePokemon(): LiveData<Resource<Pokemon>> = singlePokemon

    fun loadSinglePokemon(pokemonName: String) {
        viewModelScope.launch {
            singlePokemon.value = Resource.Loading()
            try {
                val result = repository.getPokemonInfo(pokemonName)
                if (result.data != null) {
                    val singlePokemonValue = result.data
                    singlePokemon.value = Resource.Success(singlePokemonValue)
                }
            } catch (e: Exception) {
                singlePokemon.value = Resource.Error(e.toString())
            }

        }
    }

}