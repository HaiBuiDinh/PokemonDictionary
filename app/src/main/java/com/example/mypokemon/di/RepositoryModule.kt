package com.example.mypokemon.di

import com.example.mypokemon.data.repository.PokemonRepository
import com.example.mypokemon.data.repository.PokemonRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(pokemonRepositoryImp: PokemonRepositoryImp): PokemonRepository
}