package com.ari.pokemon.ui.viewModel

sealed class Result<T> {
    class Loading<T>: Result<T>()
    class Success<T>(val result: T): Result<T>()
    class Error<T>(val error: String): Result<T>()
}