package com.ari.pokemon.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListResponseData(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<SinglePokemonData>
) : Parcelable