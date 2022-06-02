package com.example.mypokemon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mypokemon.data.ApiResource
import com.example.mypokemon.data.ApiResponse
import com.example.mypokemon.data.model.PokemonResultEntity
import com.example.mypokemon.data.model.PokemonSpeciesEntity
import com.example.mypokemon.data.model.SinglePokemonEntity
import com.example.mypokemon.data.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): PokemonRepository {
    override fun getPokemonList(): Flow<PagingData<PokemonResultEntity.Result>> = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = {remoteDataSource.getPokemonList()}
    ).flow

    override fun getDetailPokemonByName(pokemonName: String): Flow<ApiResource<SinglePokemonEntity>> =
        flow {
            emit(ApiResource.Loading())
            when (val response = remoteDataSource.getDetailPokemonByName(pokemonName).first()) {
                is ApiResponse.Success -> {
                    val data = response.data
                    emit(ApiResource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(ApiResource.Error(response.errorMessage))
                }
                is ApiResponse.Empty -> {

                }
            }
        }

    override fun getPokemonSpeciesByName(pokemonName: String): Flow<ApiResource<PokemonSpeciesEntity>> =
        flow {
            emit(ApiResource.Loading())
            when (val response = remoteDataSource.getPokemonSpeciesByName(pokemonName).first()) {
                is ApiResponse.Success -> {
                    val data = response.data
                    emit(ApiResource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(ApiResource.Error(response.errorMessage))
                }
                is ApiResponse.Empty -> {

                }
            }
        }
}