package com.ari.pokemon.data.contracts

import com.ari.pokemon.data.model.PokemonListResponseData
import com.ari.pokemon.data.model.ResultData

interface PokemonListSource {
    suspend fun getPokemonList(limit: Int = 500): ResultData<PokemonListResponseData>
}