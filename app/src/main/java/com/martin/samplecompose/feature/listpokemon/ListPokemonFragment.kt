package com.martin.samplecompose.feature.listpokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.martin.samplecompose.R
import com.martin.samplecompose.data.remote.models.PokedexListEntry
import com.martin.samplecompose.databinding.ListPokemonsFragmentBinding
import com.martin.samplecompose.util.Resource
import com.martin.samplecompose.util.setLoading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPokemonFragment : Fragment(R.layout.list_pokemons_fragment) {

    private val viewModel: ListPokemonViewModel by viewModels()
    private lateinit var binding: ListPokemonsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListPokemonsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonAdapter = PokemonAdapter { pokemon -> adapterOnClick(pokemon = pokemon) }

        binding.apply {
            rvListPokemon.apply {
                adapter = pokemonAdapter
            }
        }
        viewModel.loadPokemonList()
        viewModel.getPokemonList().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Success -> {
                    pokemonAdapter.submitList(result.data)
                    binding.pbListPokemon.setLoading(false)
                }
                is Resource.Error -> {
                    binding.pbListPokemon.setLoading(false)

                }
                is Resource.Loading -> {
                    binding.pbListPokemon.setLoading(true)

                }
            }
        })
    }

    private fun adapterOnClick(pokemon: PokedexListEntry) {
        val action =
            ListPokemonFragmentDirections.actionListPokemonFragmentToDetailPokemonFragment(pokemon.pokemonName)
        findNavController().navigate(action)
    }

}