package com.ari.pokemon.data.contracts

import com.ari.pokemon.data.model.PokemonData
import com.ari.pokemon.data.model.ResultData

interface PokemonSource {
    suspend fun getPokemonByUrl(url: String): ResultData<PokemonData>
}