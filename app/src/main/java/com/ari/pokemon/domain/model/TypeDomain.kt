package com.ari.pokemon.domain.model

import com.ari.pokemon.data.model.TypeData

data class TypeDomain(
    val slot: Int,
    val type: ItemDomain
)

fun TypeData.toDomain() = TypeDomain(
    slot = slot,
    type = type.toDomain()
)