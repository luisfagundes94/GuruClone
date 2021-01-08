package com.luisfelipe.feature_stock.utils

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

fun Fragment.horizontalRecyclerViewLayout() = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

fun Fragment.verticalRecyclerViewLayout() = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)