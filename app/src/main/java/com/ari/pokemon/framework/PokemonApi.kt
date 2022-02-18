package com.ari.pokemon.framework

import com.ari.pokemon.data.model.PokemonData
import com.ari.pokemon.data.model.PokemonListResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {

    @GET("pokemon?")
    suspend fun getPokemonList(@Query("limit") limit: Int = 500): Response<PokemonListResponseData>

//    @GET("pokemon-form/{pokemonId}/")
    @GET
    suspend fun getPokemonByUrl(@Url url: String): Response<PokemonData>
}