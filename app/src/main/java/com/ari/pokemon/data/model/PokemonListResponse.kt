package com.ari.pokemon.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<SinglePokemon>
) : Parcelable

@Parcelize
data class SinglePokemon(
    val name: String,
    val url: String
) : Parcelable
