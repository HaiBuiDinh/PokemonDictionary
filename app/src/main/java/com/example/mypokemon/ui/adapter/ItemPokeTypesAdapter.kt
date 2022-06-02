package com.example.mypokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemon.data.model.SinglePokemonEntity
import com.example.mypokemon.databinding.ItemPokemonTypesBinding
import com.example.mypokemon.utils.DiffUtils

class ItemPokeTypesAdapter: RecyclerView.Adapter<ItemPokeTypesAdapter.MyViewHolder>() {

    private var typesPoke = ArrayList<SinglePokemonEntity.Type>()

    inner class MyViewHolder(private val binding: ItemPokemonTypesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pokeTypes: SinglePokemonEntity.Type){
            binding.pokeTypes = pokeTypes
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemPokemonTypesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentType = typesPoke[position]
        holder.bind(currentType)
    }

    override fun getItemCount(): Int {
        return typesPoke.size
    }

    fun diffData(newData: SinglePokemonEntity) {
        val typesDiffUtil = DiffUtils(typesPoke, newData.types)
        val diffUtilResult = DiffUtil.calculateDiff(typesDiffUtil)
        typesPoke = newData.types as ArrayList<SinglePokemonEntity.Type> /* = java.util.ArrayList<com.example.mypokemon.data.model.SinglePokemonEntity.Type> */
        diffUtilResult.dispatchUpdatesTo(this)
    }
}