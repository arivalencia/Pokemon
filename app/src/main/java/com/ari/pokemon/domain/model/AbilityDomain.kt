package com.ari.pokemon.domain.model

import com.ari.pokemon.data.model.AbilityData

data class AbilityDomain(
    val ability: ItemDomain,
    val is_hidden: Boolean,
    val slot: Int
)

fun AbilityData.toDomain() = AbilityDomain(
    ability = ability.toDomain(),
    is_hidden = is_hidden,
    slot = slot
)