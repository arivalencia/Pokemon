package com.ari.pokemon.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideUtils {

    fun setImageCrop(imageUrl: String?, iv: ImageView?) {
        if (imageUrl == null) return
        if (imageUrl.isEmpty()) return
        if (iv == null) return

        Glide
            .with(iv.context)
            .load(imageUrl)
            .circleCrop()
            .into(iv)
    }
}