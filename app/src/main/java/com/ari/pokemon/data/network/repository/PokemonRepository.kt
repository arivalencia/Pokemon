package com.ari.pokemon.data.network.repository

import com.ari.pokemon.data.network.PokemonApi
import com.ari.pokemon.data.model.Pokemon
import com.ari.pokemon.data.model.PokemonListResponse
import com.ari.pokemon.ui.viewModel.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi
) {

    private val GENERIC_ERROR = "__Error__"

    suspend fun getPokemonList(limit: Int = 500): Result<PokemonListResponse> = try {
        val response = pokemonApi.getPokemonList()

        if(response.isSuccessful) {
            response.body()?.let {
                Result.Success(it)
            } ?: Result.Error(response.message() ?: GENERIC_ERROR)
        } else {
            Result.Error(response.message())
        }

    } catch (e: Exception) {
        Result.Error(e.message ?: GENERIC_ERROR)
    }

    suspend fun getPokemonByUrl(url: String): Result<Pokemon> = try {
        val response = pokemonApi.getPokemonByUrl(url)

        if(response.isSuccessful) {
            response.body()?.let {
                Result.Success(it)
            } ?: Result.Error(response.message() ?: GENERIC_ERROR)
        } else {
            Result.Error(response.message())
        }

    } catch (e: Exception) {
        Result.Error(e.message ?: GENERIC_ERROR)
    }

}