package com.example.mypokemon.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokemon.data.ApiResource
import com.example.mypokemon.data.model.PokemonSpeciesEntity
import com.example.mypokemon.data.model.SinglePokemonEntity
import com.example.mypokemon.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {
    private val _pokemonDetailResponse = MutableLiveData<ApiResource<SinglePokemonEntity>>()
    val pokemonDetailResponse: LiveData<ApiResource<SinglePokemonEntity>> get() = _pokemonDetailResponse


    private val _pokemonSpeciesResponse = MutableLiveData<ApiResource<PokemonSpeciesEntity>>()
    val pokemonSpeciesResponse: LiveData<ApiResource<PokemonSpeciesEntity>> get() = _pokemonSpeciesResponse

    fun getDetailPokemonByName(pokemonName: String) {
        viewModelScope.launch {
            repository.getDetailPokemonByName(pokemonName)
                .onStart { _pokemonDetailResponse.postValue(ApiResource.Loading()) }
                .catch { error ->
                    error.message?.let {
                        _pokemonDetailResponse.postValue(ApiResource.Error(it))
                    }
                }
                .collectLatest { pokemonDetail ->
                    pokemonDetail.data?.let {
                        _pokemonDetailResponse.postValue(ApiResource.Success(it))
                    }
                }
        }
    }

    fun getSpeciesPokemonByName(pokemonName: String) {
        viewModelScope.launch {
            repository.getPokemonSpeciesByName(pokemonName)
                .onStart { _pokemonSpeciesResponse.postValue(ApiResource.Loading()) }
                .catch { error ->
                    error.message?.let{
                        _pokemonSpeciesResponse.postValue(ApiResource.Error(it))
                    }
                }
                .collectLatest { pokemonSpecies ->
                    pokemonSpecies.data?.let {
                        _pokemonSpeciesResponse.postValue(ApiResource.Success(it))
                    }
                }
        }
    }
}