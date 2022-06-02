package com.example.mypokemon.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mypokemon.R
import com.example.mypokemon.data.ApiResource
import com.example.mypokemon.databinding.FragmentDetailBinding
import com.example.mypokemon.ui.adapter.ItemPokeStatsAdapter
import com.example.mypokemon.ui.adapter.ItemPokeTypesAdapter
import dagger.hilt.android.AndroidEntryPoint
/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val mDetailViewModel : DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()
    private val typesAdapter by lazy { ItemPokeTypesAdapter() }
    private val statsAdapter by lazy { ItemPokeStatsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
    }

    private fun initRecyclerView() {
        binding.rvDetailPokemonStats.apply {
            adapter = statsAdapter
            setHasFixedSize(true)
        }

        binding.rvDetailPokemonTypes.apply {
            adapter = typesAdapter
            setHasFixedSize(true)
        }
    }

    private fun initData() {
        val pokemonName: String = args.pokemonName

        mDetailViewModel.getDetailPokemonByName(pokemonName)
        mDetailViewModel.pokemonDetailResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiResource.Success -> {

                }
                is ApiResource.Error -> {

                }
                is ApiResource.Loading -> {

                }
            }

        }

        mDetailViewModel.getSpeciesPokemonByName(pokemonName)
        mDetailViewModel.pokemonSpeciesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiResource.Success -> {

                }
                is ApiResource.Error -> {

                }
                is ApiResource.Loading -> {

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}