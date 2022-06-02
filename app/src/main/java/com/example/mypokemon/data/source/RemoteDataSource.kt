package com.example.mypokemon.data.source

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.mypokemon.data.ApiResource
import com.example.mypokemon.data.ApiResponse
import com.example.mypokemon.data.model.PokemonResultEntity
import com.example.mypokemon.data.model.PokemonSpeciesEntity
import com.example.mypokemon.data.model.SinglePokemonEntity
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getPokemonList(): PagingSource<Int, PokemonResultEntity.Result>
    fun getDetailPokemonByName(pokemonName: String): Flow<ApiResponse<SinglePokemonEntity>>
    fun getPokemonSpeciesByName(pokemonName: String): Flow<ApiResponse<PokemonSpeciesEntity>>
}