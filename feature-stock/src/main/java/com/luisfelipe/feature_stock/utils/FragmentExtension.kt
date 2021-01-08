package com.luisfelipe.feature_stock.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

fun Fragment.horizontalRecyclerViewLayout() = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

fun Fragment.verticalRecyclerViewLayout() = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, message, duration).show()
}
