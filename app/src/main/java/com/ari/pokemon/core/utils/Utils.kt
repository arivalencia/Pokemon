package com.ari.pokemon.core.utils

object Utils {

    fun isValidString(s: String?): Boolean {
        if (s == null) return false
        return s.isNotEmpty()
    }
}