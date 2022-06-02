package com.example.mypokemon.ui.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mypokemon.databinding.ItemLoadStateAdapterBinding

class ItemLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<ItemLoadStateAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ItemLoadStateAdapterBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnLoadAdapter.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                pbLoadAdapter.isVisible = loadState is LoadState.Loading
                btnLoadAdapter.isVisible = loadState is LoadState.Error
                tvLoadAdapter.isVisible = loadState is LoadState.Error

                if (loadState is LoadState.Error) {
                    tvLoadAdapter.text = loadState.error.localizedMessage
                }
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MyViewHolder {
        val binding = ItemLoadStateAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}