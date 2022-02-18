package com.ari.pokemon.di

import com.ari.pokemon.data.contracts.PokemonListSource
import com.ari.pokemon.data.contracts.PokemonSource
import com.ari.pokemon.data.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Inject
    @Provides
    fun providePokemonRepository(
        pokemonListSource: PokemonListSource,
        pokemonSource: PokemonSource
    ) : PokemonRepository = PokemonRepository(
        pokemonListSource, pokemonSource
    )

}