package com.example.mypokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemon.data.model.PokemonResultEntity
import com.example.mypokemon.databinding.ItemPokemonListBinding

class PokemonPagingAdapter : PagingDataAdapter<PokemonResultEntity.Result, PokemonPagingAdapter.MyViewHolder>(POKEMON_DIFF) {

    class MyViewHolder(private val binding: ItemPokemonListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(result: PokemonResultEntity.Result) {
            binding.pokemonResults = result
            binding.executePendingBindings()
        }
    }

    companion object {
        private val POKEMON_DIFF = object : DiffUtil.ItemCallback<PokemonResultEntity.Result>() {
            override fun areItemsTheSame(
                oldItem: PokemonResultEntity.Result,
                newItem: PokemonResultEntity.Result
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: PokemonResultEntity.Result,
                newItem: PokemonResultEntity.Result
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPokemon = getItem(position)
        currentPokemon?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}