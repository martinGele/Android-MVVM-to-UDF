package com.martin.samplecompose.feature.listpokemon

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.martin.samplecompose.R
import com.martin.samplecompose.data.remote.models.PokedexListEntry
import com.martin.samplecompose.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPokemonFragment : Fragment(R.layout.list_pokemons_fragment) {

    private val viewModel: ListPokemonViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonAdapter = PokemonAdapter { pokemon -> adapterOnClick(pokemon = pokemon) }

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.apply {
            adapter = pokemonAdapter
        }
        viewModel.loadPokemonList()
        viewModel.getPokemonList().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Success -> {
                    pokemonAdapter.submitList(result.data)
                }
                is Resource.Error -> {
                    Log.d("printError", result.message.toString())
                }
                is Resource.Loading -> {
                    Log.d("Loading...", ".........")
                }
            }
        })
    }

    private fun adapterOnClick(pokemon: PokedexListEntry) {
        val action =   ListPokemonFragmentDirections.actionListPokemonFragmentToDetailPokemonFragment(pokemon.pokemonName)
        view?.findNavController()?.navigate(action)
    }


}