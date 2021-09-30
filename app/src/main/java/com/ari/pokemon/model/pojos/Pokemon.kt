package com.ari.pokemon.model.pojos

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name: String,
    val abilities: List<Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val height: Int,
    val id: Int,
    val types: List<Type>,
    val weight: Int,
    val sprites: Sprite
)

data class Ability(
    val ability: Item,
    val is_hidden: Boolean,
    val slot: Int
)

data class Item(
    val name: String,
    val url: String
)

data class Type(
    val slot: Int,
    val type: Item
)

data class Sprite(
    @SerializedName("front_default")
    val imageUrl: String
)
