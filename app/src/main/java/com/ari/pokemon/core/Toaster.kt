package com.ari.pokemon.core

import android.content.Context
import android.widget.Toast

object Toaster {

    fun show(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}