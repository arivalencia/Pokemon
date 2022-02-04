package com.ari.pokemon.ui.view.bindingAdapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ari.pokemon.R
import com.ari.pokemon.core.extensions.isValid
import com.ari.pokemon.core.utils.GlideUtils
import com.ari.pokemon.core.utils.Utils

@BindingAdapter("load_image_crop")
fun loadImageCrop(iv: ImageView, imageUrl: String?) {
    if (Utils.isValidString(imageUrl)) {
        // Set image
        GlideUtils.setImageCrop(imageUrl, iv)
    } else {
        // Set default image
        iv.setImageResource(R.drawable.circle_white)
    }
}

@BindingAdapter("text_as_int")
fun loadImageCrop(tv: TextView, num: Int?) {
    tv.setText("$num")
}