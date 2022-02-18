package com.ari.pokemon.data.repository

import com.ari.pokemon.data.contracts.PokemonListSource
import com.ari.pokemon.data.contracts.PokemonSource
import com.ari.pokemon.data.model.PokemonData
import com.ari.pokemon.data.model.PokemonListResponseData
import com.ari.pokemon.data.model.ResultData
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonListSource: PokemonListSource,
    private val pokemonSource: PokemonSource
) {

    suspend fun getPokemonList(limit: Int = 500): ResultData<PokemonListResponseData> = pokemonListSource.getPokemonList(limit)

    suspend fun getPokemonByUrl(url: String): ResultData<PokemonData> = pokemonSource.getPokemonByUrl(url)

}