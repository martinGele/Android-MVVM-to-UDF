package com.martin.samplecompose.feature.detailpokemon

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.martin.samplecompose.R
import com.martin.samplecompose.databinding.DetailPokemonFragmentBinding
import com.martin.samplecompose.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonFragment : Fragment(R.layout.detail_pokemon_fragment) {

    private val args: DetailPokemonFragmentArgs by navArgs()
    private val viewModel: DetailPokemonViewModel by viewModels()
    private lateinit var binding: DetailPokemonFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DetailPokemonFragmentBinding.inflate(inflater,    container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadSinglePokemon(pokemonName = args.pokemonName.lowercase())
        viewModel.getSinglePokemon().observe(viewLifecycleOwner, {pokemon->
            when (pokemon) {
                is Resource.Success -> {
                    if (pokemon.data != null) {
                        binding.apply {
                            ivPokemonDetail.load(pokemon.data.sprites.frontShiny)
                            tvPokemonName.text = pokemon.data.species.name
                        }
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