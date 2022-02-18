package com.ari.pokemon.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SinglePokemonData(
    val name: String,
    val url: String
) : Parcelable