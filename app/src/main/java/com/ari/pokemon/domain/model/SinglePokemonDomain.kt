package com.ari.pokemon.domain.model

import android.os.Parcelable
import com.ari.pokemon.data.model.SinglePokemonData
import kotlinx.parcelize.Parcelize

@Parcelize
data class SinglePokemonDomain(
    val name: String,
    val url: String
) : Parcelable

fun SinglePokemonData.toDomain() = SinglePokemonDomain(
    name = name,
    url = url
)