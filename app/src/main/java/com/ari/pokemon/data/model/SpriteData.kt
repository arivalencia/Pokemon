package com.ari.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class SpriteData(
    @SerializedName("front_default")
    val imageUrl: String
)