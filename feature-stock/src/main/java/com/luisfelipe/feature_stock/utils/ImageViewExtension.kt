package com.luisfelipe.feature_stock.utils

import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

fun ShapeableImageView.load(imageUrl: String) {
    Picasso.get().load(imageUrl).into(this)
}