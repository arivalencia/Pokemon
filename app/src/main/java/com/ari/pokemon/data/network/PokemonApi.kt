package com.ari.pokemon.data.network

import com.ari.pokemon.data.model.Pokemon
import com.ari.pokemon.data.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {

    @GET("pokemon?")
    suspend fun getPokemonList(@Query("limit") limit: Int = 500): Response<PokemonListResponse>

//    @GET("pokemon-form/{pokemonId}/")
    @GET
    suspend fun getPokemonByUrl(@Url url: String): Response<Pokemon>
}