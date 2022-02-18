package com.ari.pokemon.domain.model

import com.ari.pokemon.data.model.SpriteData
import com.google.gson.annotations.SerializedName

data class SpriteDomain(
    @SerializedName("front_default")
    val imageUrl: String
)

fun SpriteData.toDomain() = SpriteDomain(
    imageUrl = imageUrl
)