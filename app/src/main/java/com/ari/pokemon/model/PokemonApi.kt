package com.ari.pokemon.model

import com.ari.pokemon.model.pojos.Pokemon
import com.ari.pokemon.model.pojos.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {

    @GET("pokemon?")
    fun getPokemonList(@Query("limit") limit: Int = 500): Call<PokemonListResponse>

//    @GET("pokemon-form/{pokemonId}/")
    @GET
    fun getPokemonByUrl(@Url url: String): Call<Pokemon>
}