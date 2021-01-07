package com.luisfelipe.feature_stock.data.local.service

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.luisfelipe.feature_stock.domain.models.Stock

class StockService {
    fun jsonToClass(context: Context, @RawRes resourceId: Int): Stock =
        Gson().fromJson(context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }, Stock::class.java)
}