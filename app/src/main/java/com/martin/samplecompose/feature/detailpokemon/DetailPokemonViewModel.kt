package com.martin.samplecompose.feature.detailpokemon

import androidx.lifecycle.ViewModel
import com.martin.samplecompose.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {
}