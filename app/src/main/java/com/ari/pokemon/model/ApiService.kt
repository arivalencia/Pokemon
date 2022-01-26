package com.ari.pokemon.model

import com.ari.pokemon.core.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofitAuth: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitAuth.create(serviceClass)
    }

}