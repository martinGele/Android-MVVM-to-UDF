package com.martin.samplecompose.feature.listpokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.martin.samplecompose.data.remote.models.PokedexListEntry
import com.martin.samplecompose.databinding.PokemonItemBinding

class PokemonAdapter(private val onClick: (PokedexListEntry) -> Unit) :
    ListAdapter<PokedexListEntry, PokemonAdapter.PokemonViewHolder>(FlowerDiffCallback) {

    class PokemonViewHolder(
        private val binding: PokemonItemBinding,
        val onClick: (PokedexListEntry) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        private var currentPokemon: PokedexListEntry? = null

        init {
            binding.root.setOnClickListener {
                currentPokemon?.let {
                    onClick(it)
                }
            }
        }

        fun bind(pokemon: PokedexListEntry) {
            currentPokemon = pokemon
            binding.apply {
                ivPokemon.load(pokemon.imageUrl)
                tvName.text = pokemon.pokemonName
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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