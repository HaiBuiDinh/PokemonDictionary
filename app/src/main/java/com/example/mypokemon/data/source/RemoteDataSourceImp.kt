package com.example.mypokemon.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mypokemon.data.ApiResource
import com.example.mypokemon.data.ApiResponse
import com.example.mypokemon.data.model.PokemonResultEntity
import com.example.mypokemon.data.model.PokemonSpeciesEntity
import com.example.mypokemon.data.model.SinglePokemonEntity
import com.example.mypokemon.data.network.ApiService
import com.example.mypokemon.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override fun getPokemonList(): PagingSource<Int, PokemonResultEntity.Result> {
        return object : PagingSource<Int, PokemonResultEntity.Result>() {
            override fun getRefreshKey(state: PagingState<Int, PokemonResultEntity.Result>): Int? {
                return state.anchorPosition
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResultEntity.Result> {
                return try {
                    val pokemonOffset = params.key ?: Constants.OFFSET

                    val response = apiService.getPokemonList(pokemonOffset, Constants.LIMIT)
                    val pokemon = response.body()?.results ?: emptyList()

                    val prevKey = if (pokemonOffset == Constants.OFFSET) null else pokemonOffset - Constants.LIMIT
                    val nextKey = if (pokemon.size< 20 || pokemon.isEmpty()) null else pokemonOffset + Constants.LIMIT

                    LoadResult.Page(
                        data = pokemon,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                } catch (e:IOException) {
                    LoadResult.Error(e)
                } catch (e: HttpException) {
                    LoadResult.Error(e)
                }
            }

        }
    }

    override fun getDetailPokemonByName(pokemonName: String): Flow<ApiResponse<SinglePokemonEntity>> {
        return flow {
            try {
                val response= apiService.getSinglePokemonByName(pokemonName)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getPokemonSpeciesByName(pokemonName: String): Flow<ApiResponse<PokemonSpeciesEntity>> {
        return flow {
            try {
                val response = apiService.getPokemonSpecies(pokemonName)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }
}