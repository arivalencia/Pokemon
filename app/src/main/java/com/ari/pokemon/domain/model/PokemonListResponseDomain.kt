package com.ari.pokemon.domain.model

import android.os.Parcelable
import com.ari.pokemon.data.model.PokemonListResponseData
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListResponseDomain(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<SinglePokemonDomain>
) : Parcelable

fun PokemonListResponseData.toDomain() = PokemonListResponseDomain(
    count = count,
    next = next,
    previous = previous,
    results = results.map { it.toDomain() }
)