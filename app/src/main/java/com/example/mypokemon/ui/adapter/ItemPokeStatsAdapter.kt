package com.example.mypokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemon.data.model.SinglePokemonEntity
import com.example.mypokemon.databinding.ItemPokemonStatsBinding
import com.example.mypokemon.utils.DiffUtils

class ItemPokeStatsAdapter: RecyclerView.Adapter<ItemPokeStatsAdapter.MyViewHolder>() {
    private var statsPokemon = ArrayList<SinglePokemonEntity.Stat>()
    inner class MyViewHolder(private val binding: ItemPokemonStatsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(statPoke: SinglePokemonEntity.Stat) {
            binding.pokeStats = statPoke
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPokemonStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentStat = statsPokemon[position]
        holder.bind(currentStat)
    }

    override fun getItemCount(): Int {
        return statsPokemon.size
    }

    fun diffStat(newData: SinglePokemonEntity) {
        val statDiffUtils = DiffUtils(statsPokemon, newData.stats)
        val diffUtilResult = DiffUtil.calculateDiff(statDiffUtils)
        statsPokemon = newData.stats as ArrayList<SinglePokemonEntity.Stat> /* = java.util.ArrayList<com.example.mypokemon.data.model.SinglePokemonEntity.Stat> */
        diffUtilResult.dispatchUpdatesTo(this)
    }
}