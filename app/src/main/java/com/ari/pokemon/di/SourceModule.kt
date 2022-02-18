package com.ari.pokemon.di

import com.ari.pokemon.framework.PokemonApi
import com.ari.pokemon.data.contracts.PokemonListSource
import com.ari.pokemon.data.contracts.PokemonSource
import com.ari.pokemon.framework.PokemonListSourceImpl
import com.ari.pokemon.framework.PokemonSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Inject
    @Provides
    fun providePokemonListSource(pokemonApi: PokemonApi): PokemonListSource = PokemonListSourceImpl(pokemonApi)

    @Inject
    @Provides
    fun providePokemonSource(pokemonApi: PokemonApi): PokemonSource = PokemonSourceImpl(pokemonApi)
}