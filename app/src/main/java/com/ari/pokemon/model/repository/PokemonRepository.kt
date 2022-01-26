package com.ari.pokemon.model.repository

import com.ari.pokemon.model.ApiService
import com.ari.pokemon.model.PokemonApi
import com.ari.pokemon.model.pojos.Pokemon
import com.ari.pokemon.model.pojos.PokemonListResponse
import com.ari.pokemon.viewModel.Result

class PokemonRepository {

    private val api: PokemonApi = ApiService.createService(PokemonApi::class.java)

    private val GENERIC_ERROR = "__Error__"

    suspend fun getPokemonList(limit: Int = 500): Result<PokemonListResponse> = try {
        val response = api.getPokemonList()

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
        val response = api.getPokemonByUrl(url)

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