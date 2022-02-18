package com.ari.pokemon.domain.model

import com.ari.pokemon.data.model.PokemonData
import com.google.gson.annotations.SerializedName

data class PokemonDomain(
    val name: String,
    val abilities: List<AbilityDomain>,

    @SerializedName("base_experience")
    val baseExperience: Int,

    val height: Int,
    val id: Int,
    val types: List<TypeDomain>,
    val weight: Int,
    val sprites: SpriteDomain
)

fun PokemonData.toDomain() = PokemonDomain(
    name = name,
    abilities = abilities.map { it.toDomain() },
    baseExperience = baseExperience,
    height = height,
    id = id,
    types = types.map { it.toDomain() },
    weight = weight,
    sprites = sprites.toDomain()
)