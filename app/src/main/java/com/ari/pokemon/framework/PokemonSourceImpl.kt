package com.ari.pokemon.framework

import com.ari.pokemon.data.contracts.PokemonSource
import com.ari.pokemon.data.model.PokemonData
import com.ari.pokemon.data.model.ResultData
import javax.inject.Inject

class PokemonSourceImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonSource {

    private val GENERIC_ERROR = "__Error__"

    override suspend fun getPokemonByUrl(url: String): ResultData<PokemonData> = try {
        val response = pokemonApi.getPokemonByUrl(url)

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