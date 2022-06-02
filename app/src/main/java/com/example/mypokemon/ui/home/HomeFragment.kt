package com.example.mypokemon.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.mypokemon.R
import com.example.mypokemon.databinding.FragmentHomeBinding
import com.example.mypokemon.ui.adapter.ItemLoadStateAdapter
import com.example.mypokemon.ui.adapter.PokemonPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mHomeViewModel: HomeViewModel by viewModels()
    private val adapter by lazy { PokemonPagingAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.apply {
            rvPokemonList.setHasFixedSize(true)
            rvPokemonList.adapter = adapter.withLoadStateHeaderAndFooter(
                header = ItemLoadStateAdapter { adapter.retry() },
                footer = ItemLoadStateAdapter { adapter.retry() }
            )

            btnErrorLoad.setOnClickListener { adapter.retry() }

            adapter.addLoadStateListener { loadState ->
                pbPokemonList.isVisible = loadState.source.refresh is LoadState.Loading
                tvErrorLoad.isVisible = loadState.source.refresh is LoadState.Error
                btnErrorLoad.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            mHomeViewModel.pokemonPaging.collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}