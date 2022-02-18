package com.ari.pokemon.domain.model

import com.ari.pokemon.data.model.ItemData

data class ItemDomain(
    val name: String,
    val url: String
)

fun ItemData.toDomain() = ItemDomain(
    name = name,
    url = url
)