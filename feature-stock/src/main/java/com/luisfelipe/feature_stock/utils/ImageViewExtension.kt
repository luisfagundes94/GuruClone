package com.luisfelipe.feature_stock.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(imageUrl: String) {
    Picasso.get().load(imageUrl).into(this)
}
