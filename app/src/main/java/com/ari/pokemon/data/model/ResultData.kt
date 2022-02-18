package com.ari.pokemon.data.model

sealed class ResultData<T> {
    class Success<T>(val result: T): ResultData<T>()
    class Error<T>(val error: String): ResultData<T>()
}