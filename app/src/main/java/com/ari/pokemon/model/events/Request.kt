package com.ari.pokemon.model.events

interface Request<T> {
    fun onSuccess(response: T)
    fun onFailure(error: String)
}