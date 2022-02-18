package com.ari.pokemon.framework

import com.ari.pokemon.data.contracts.PokemonListSource
import com.ari.pokemon.data.model.PokemonListResponseData
import com.ari.pokemon.data.model.ResultData
import javax.inject.Inject

class PokemonListSourceImpl @Inject constructor(
    private val pokemonApi: PokemonApi
): PokemonListSource {

    private val GENERIC_ERROR = "__Error__"

    override suspend fun getPokemonList(limit: Int): ResultData<PokemonListResponseData> = try {
        val response = pokemonApi.getPokemonList(limit)

        if(response.isSuccessful) {
            response.body()?.let {
                ResultData.Success(it)
            } ?: ResultData.Error(response.message() ?: GENERIC_ERROR)
        } else {
            ResultData.Error(response.message())
        }

    } catch (e: Exception) {
        ResultData.Error(e.message ?: GENERIC_ERROR)
    }

}