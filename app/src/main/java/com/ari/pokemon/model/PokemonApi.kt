package com.ari.pokemon.model

import com.ari.pokemon.model.pojos.Pokemon
import com.ari.pokemon.model.pojos.PokemonListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {

    @GET("pokemon?")
    suspend fun getPokemonList(@Query("limit") limit: Int = 500): Response<PokemonListResponse>

//    @GET("pokemon-form/{pokemonId}/")
    @GET
    suspend fun getPokemonByUrl(@Url url: String): Response<Pokemon>
}