package com.example.mypokemon.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import coil.load
import com.example.mypokemon.R
import com.example.mypokemon.data.ApiResource
import com.example.mypokemon.data.model.PokemonSpeciesEntity
import com.example.mypokemon.data.model.SinglePokemonEntity
import com.example.mypokemon.databinding.FragmentDetailBinding
import com.example.mypokemon.ui.adapter.ItemPokeStatsAdapter
import com.example.mypokemon.ui.adapter.ItemPokeTypesAdapter
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color
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

    private var isLoading: Boolean? = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        initRecyclerView()
        initData()
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
                    initPokemonDetail(response.data!!)
                    isLoading(false)
                }
                is ApiResource.Error -> {
                    isLoading(false)
                }
                is ApiResource.Loading -> {
                    isLoading(true)
                }
            }

        }

        mDetailViewModel.getSpeciesPokemonByName(pokemonName)
        mDetailViewModel.pokemonSpeciesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ApiResource.Success -> {
                    initPokemonSpecies(response.data!!)
                }
                is ApiResource.Error -> {

                }
                is ApiResource.Loading -> {

                }
            }
        }
    }

    private fun initPokemonDetail(data: SinglePokemonEntity) {
        binding.apply {
            val pokemonImage = data.sprites?.other?.officialArtwork?.frontDefault

            setBackgroundPalette(ivDetailPokemon, pokemonImage, shapeImageDetail)
            tvDetailPokemonNumber.text = getString(R.string.pokemon_number_format, data.id)
            tvDetailPokemonName.text = data.name.replaceFirstChar { it.uppercase() }

            tvDetailBaseXp.text = data.baseExperience.toString()
            tvDetailHeight.text = getString(R.string.pokemon_format_height, (data.height?.times(10)))
            tvDetailWeight.text = getString(R.string.pokemon_format_weight, (data.weight?.div(10.0)))

            statsAdapter.diffStat(data)
            typesAdapter.diffData(data)

        }
    }

    private fun initPokemonSpecies(data: PokemonSpeciesEntity) {
        binding.apply {
            var flavorText = data.flavorTextEntries[1].flavorText
            flavorText = flavorText.replace("POKéMON", "Pokémon")
            flavorText = flavorText.replace("\n", " ")

            tvDetailPokemonDesc.text = flavorText
            tvDetailCatchRate.text = data.captureRate.toString()
        }
    }

    private fun setBackgroundPalette(view: ImageView, url: String?, shapeImage: View) {
        view.load(url) {
            allowHardware(false)
            crossfade(200)
            error(R.drawable.ic_launcher_foreground)
            listener(
                onSuccess = { _, result ->
                    Palette.Builder(result.drawable.toBitmap()).generate() { palette ->
                        val light = palette?.lightVibrantSwatch?.rgb
                        val domain = palette?.dominantSwatch?.rgb
                        if (domain != null) {
                            if (light != null) {
                                Rainbow(shapeImage).palette {
                                    +color(domain)
                                    +color(light)
                                }.background(orientation = RainbowOrientation.TOP_BOTTOM)
                            } else {
                                shapeImage.setBackgroundColor(domain)
                            }
                        }
                    }
                }
            )
        }
    }

    private fun isLoading(boolean: Boolean) {
        binding.apply {
            if (boolean) {
                isLoading = true
                rvDetailPokemonTypes.visibility = View.GONE
                rvDetailPokemonStats.visibility = View.GONE
                pbDetail.visibility = View.VISIBLE
                scrollDetail.visibility = View.GONE
            } else {
                isLoading = false
                rvDetailPokemonTypes.visibility = View.VISIBLE
                rvDetailPokemonStats.visibility = View.VISIBLE
                pbDetail.visibility = View.GONE
                scrollDetail.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}