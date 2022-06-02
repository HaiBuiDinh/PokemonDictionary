package com.example.mypokemon.data.network

import com.example.mypokemon.data.model.PokemonResultEntity
import com.example.mypokemon.data.model.PokemonSpeciesEntity
import com.example.mypokemon.data.model.SinglePokemonEntity
import com.example.mypokemon.utils.Constants.QUERY_LIMIT
import com.example.mypokemon.utils.Constants.QUERY_OFFSET
import com.example.mypokemon.utils.Constants.QUERY_POKEMON_NAME
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query(QUERY_OFFSET) offset: Int,
        @Query(QUERY_LIMIT) limit: Int
    ): Response<PokemonResultEntity>

    @GET("pokemon/{name}")
    suspend fun getSinglePokemonByName(
        @Path(QUERY_POKEMON_NAME) name: String
    ): SinglePokemonEntity

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(
        @Path(QUERY_POKEMON_NAME) name: String
    ): PokemonSpeciesEntity
}