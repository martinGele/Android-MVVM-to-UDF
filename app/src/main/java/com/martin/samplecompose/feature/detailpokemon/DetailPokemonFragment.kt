package com.martin.samplecompose.feature.detailpokemon

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.martin.samplecompose.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonFragment : Fragment(R.layout.detail_pokemon_fragment) {
    private val args: DetailPokemonFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageViewPokemon: ImageView = view.findViewById(R.id.iv_pokemon_detail)
        val textViewPokemon: TextView = view.findViewById(R.id.tv_pokemon_name)

        imageViewPokemon.load(args.pokemon.imageUrl)
        textViewPokemon.text = args.pokemon.pokemonName


    }

}