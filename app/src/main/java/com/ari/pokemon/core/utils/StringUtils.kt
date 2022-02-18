package com.ari.pokemon.core.utils

object StringUtils {

    fun isValidString(s: String?): Boolean {
        if (s == null) return false
        return s.isNotEmpty()
    }
}