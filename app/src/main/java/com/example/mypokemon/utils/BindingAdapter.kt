package com.example.mypokemon.utils

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.palette.graphics.Palette
import coil.load

import com.example.mypokemon.R
import com.example.mypokemon.data.model.PokemonResultEntity
import com.example.mypokemon.data.model.SinglePokemonEntity
import com.example.mypokemon.ui.home.HomeFragmentDirections
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

object BindingAdapter {
    @BindingAdapter(
        "navigate_to_pokemon_detail"
    )
    @JvmStatic
    fun navigateMoveToDetail(view: MaterialCardView, pokemonId: String) {
        view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(pokemonId)
            view.findNavController().navigate(action)
        }
    }

    @BindingAdapter(
        "palette_image",
        "palette_card"
    )
    @JvmStatic
    fun homePokemonImagePalette(view: ImageView, url: String, paletteCard: MaterialCardView){
        view.load(extractPokemonImage(url)) {
            allowHardware(false)
            crossfade(200)
            error(R.drawable.ic_launcher_foreground)
            listener(
                onSuccess = {_, result ->
                    Palette.Builder(result.drawable.toBitmap()).generate() {palette ->
                        val rgb = palette?.dominantSwatch?.rgb
                        rgb?.let {
                            paletteCard.setCardBackgroundColor(rgb)
                        }
                    }
                }
            )
        }
    }

    @BindingAdapter(
        "lower_to_upper"
    )
    @JvmStatic
    fun lowerToUpper(view: TextView, data: PokemonResultEntity.Result) {
        view.text = data.name.replaceFirstChar { it.uppercase() }
    }

    @BindingAdapter(
        "pokemon_types"
    )
    @JvmStatic
    fun pokemonType(view: Chip, data: SinglePokemonEntity.Type) {
        view.text = data.type.name
        view.setChipBackgroundColorResource(getTypeColor(data))
    }

    @BindingAdapter(
        "stat_pokemon_string"
    )
    @JvmStatic
    fun statPokemonString(view: TextView, data: SinglePokemonEntity.Stat) {
        if( data.stat.name.contains("-")) {
            val first = data.stat.name.substringBefore("-").replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT)
                else it.toString()
            }

            val second = data.stat.name.substringAfter("-").replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT)
                else it.toString()
            }
            "$first - $second".also { view.text = it }
        } else view.text = data.stat.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()}
    }

    @BindingAdapter(
        "stats_pokemon_progress"
    )
    @JvmStatic
    fun statPokemonProgress(view: ProgressBar, data: SinglePokemonEntity.Stat) {
        view.secondaryProgress = 255
        view.max = 255
        CoroutineScope(Dispatchers.Main).launch {
            var state = 0
            while (state <= data.baseStat) {
                view.progress = state
                state++
                delay(10)
            }
        }
    }

    @BindingAdapter(
        "stats_pokemon"
    )
    @JvmStatic
    fun statsPokemon(view: TextView, data: SinglePokemonEntity.Stat) {
        (data.baseStat.toString() + buildString {
            append(" / 300")
        }).also { view.text = it }
    }
}