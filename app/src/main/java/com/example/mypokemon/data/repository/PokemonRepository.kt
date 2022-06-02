package com.example.mypokemon.data.repository

import androidx.paging.PagingData
import com.example.mypokemon.data.ApiResource
import com.example.mypokemon.data.model.PokemonResultEntity
import com.example.mypokemon.data.model.PokemonSpeciesEntity
import com.example.mypokemon.data.model.SinglePokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<PokemonResultEntity.Result>>
    fun getDetailPokemonByName(pokemonName: String): Flow<ApiResource<SinglePokemonEntity>>
    fun getPokemonSpeciesByName(pokemonName: String): Flow<ApiResource<PokemonSpeciesEntity>>
}