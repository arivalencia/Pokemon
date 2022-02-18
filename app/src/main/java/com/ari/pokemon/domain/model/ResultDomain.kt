package com.ari.pokemon.domain.model

import com.ari.pokemon.data.model.ResultData

sealed class ResultDomain<T> {
    class Loading<T>: ResultDomain<T>()
    class Success<T>(val result: T): ResultDomain<T>()
    class Error<T>(val error: String): ResultDomain<T>()
}

fun <T> ResultData<T>.toDomain(): ResultDomain<T> = when(this) {
    is ResultData.Error -> ResultDomain.Error(error)
    is ResultData.Success -> ResultDomain.Success(result)
}