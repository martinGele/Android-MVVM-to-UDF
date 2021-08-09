package com.martin.samplecompose.feature.detailpokemon

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.martin.samplecompose.R
import com.martin.samplecompose.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonFragment : Fragment(R.layout.detail_pokemon_fragment) {

    private val args: DetailPokemonFragmentArgs by navArgs()
    private val viewModel: DetailPokemonViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonDetailImage: ImageView = view.findViewById(R.id.iv_pokemon_detail)
        val pokemonNameTextView: TextView = view.findViewById(R.id.tv_pokemon_name)
        viewModel.loadSinglePokemon(pokemonName = args.pokemonName.lowercase())
        viewModel.getSinglePokemon().observe(viewLifecycleOwner, { pokemon ->
            when (pokemon) {
                is Resource.Success -> {
                    if (pokemon.data != null) {
                        pokemonDetailImage.load(pokemon.data.sprites.frontShiny)
                        pokemonNameTextView.text = pokemon.data.species.name
                    }
                }
                is Resource.Error -> {
                    Log.d("Error...", pokemon.message.toString())
                }
                is Resource.Loading -> {
                    Log.d("Loading...", "...")
                }
            }
        }
        )


    }
}