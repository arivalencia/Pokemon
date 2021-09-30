package com.ari.pokemon.model.repository

import com.ari.pokemon.model.ApiService
import com.ari.pokemon.model.PokemonApi
import com.ari.pokemon.model.events.Request
import com.ari.pokemon.model.pojos.Pokemon
import com.ari.pokemon.model.pojos.PokemonListResponse
import com.ari.pokemon.tools.constants.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository {

    private val api: PokemonApi = ApiService.createService(PokemonApi::class.java)

//    private val INSTANCE: PokemonListRepository = PokemonListRepository()

//    fun getInstance(): PokemonListRepository {
//        if (INSTANCE == null ) INSTANCE = PokemonListRepository()
//        return INSTANCE
//    }

    fun getPokemonList(limit: Int = 500, request: Request<PokemonListResponse>) {
        api.getPokemonList(limit).enqueue(object: Callback<PokemonListResponse>{
            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                if (response.isSuccessful) {
                    request.onSuccess(response.body()!!)
                } else {
                    request.onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                request.onFailure(t.message ?: Constants.SERVER_ERROR)
            }
        })
    }

    fun getPokemonByUrl(url: String, request: Request<Pokemon>) {
        api.getPokemonByUrl(url).enqueue(object: Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    request.onSuccess(response.body()!!)
                } else {
                    request.onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                request.onFailure(t.message ?: Constants.SERVER_ERROR)
            }
        })
    }


}