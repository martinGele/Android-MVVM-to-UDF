package com.martin.samplecompose.feature.listpokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.martin.samplecompose.R
import com.martin.samplecompose.data.remote.models.PokedexListEntry


class PokemonAdapter(private val onClick: (PokedexListEntry) -> Unit) :
    ListAdapter<PokedexListEntry, PokemonAdapter.PokemonViewHolder>(FlowerDiffCallback) {

    /* ViewHolder for Pokemon list entry, takes in the inflated view and the onClick behavior. */
    class PokemonViewHolder(itemView: View, val onClick: (PokedexListEntry) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val pokemonNameTextView: TextView = itemView.findViewById(R.id.tv_name)
        private val pokemonImageView: ImageView = itemView.findViewById(R.id.iv_pokemon)
        private var currentPokemon: PokedexListEntry? = null

        init {
            itemView.setOnClickListener {
                currentPokemon?.let {
                    onClick(it)
                }
            }
        }

        fun bind(pokemon: PokedexListEntry) {
            currentPokemon = pokemon
            pokemonImageView.load(pokemon.imageUrl)
            pokemonNameTextView.text = pokemon.pokemonName


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)
        return PokemonViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)

    }
}

object FlowerDiffCallback : DiffUtil.ItemCallback<PokedexListEntry>() {
    override fun areItemsTheSame(oldItem: PokedexListEntry, newItem: PokedexListEntry): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PokedexListEntry, newItem: PokedexListEntry): Boolean {
        return oldItem.pokemonName == newItem.pokemonName
    }
}