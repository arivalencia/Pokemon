package com.ari.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokemonData(
    val name: String,
    val abilities: List<AbilityData>,

    @SerializedName("base_experience")
    val baseExperience: Int,

    val height: Int,
    val id: Int,
    val types: List<TypeData>,
    val weight: Int,
    val sprites: SpriteData
)