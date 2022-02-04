package com.ari.pokemon.core.extensions

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Return [false] if this nullable String is `null` or `empty`
 */
@ExperimentalContracts
fun String?.isValid(): Boolean {
    contract {
        returns(false) implies (this@isValid == null)
    }

    return this != null && this.isNotEmpty()
}