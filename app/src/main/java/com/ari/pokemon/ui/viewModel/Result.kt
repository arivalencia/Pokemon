package com.ari.pokemon.ui.viewModel

sealed class Result<T>(
    val result: T? = null,
    val error: String? = null
) {
    class Loading<T> : Result<T>()
    class Success<T>(result: T) : Result<T>(result, null)
    class Error<T>(error: String) : Result<T>(null, error)
}