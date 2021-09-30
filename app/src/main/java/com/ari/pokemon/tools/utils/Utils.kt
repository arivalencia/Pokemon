package com.ari.pokemon.tools.utils

object Utils {

    fun isValidString(s: String?): Boolean {
        if (s == null) return false
        return s.isNotEmpty()
    }
}