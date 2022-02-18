package com.ari.pokemon.di

import com.ari.pokemon.data.repository.PokemonRepository
import com.ari.pokemon.usecases.GetPokemonByUrl
import com.ari.pokemon.usecases.GetPokemonList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Inject
    @Provides
    fun provideGetPokemonList(pokemonRepository: PokemonRepository) : GetPokemonList = GetPokemonList(
        pokemonRepository
    )

    @Inject
    @Provides
    fun provideGetPokemonByUrl(pokemonRepository: PokemonRepository) : GetPokemonByUrl = GetPokemonByUrl(
        pokemonRepository
    )
}