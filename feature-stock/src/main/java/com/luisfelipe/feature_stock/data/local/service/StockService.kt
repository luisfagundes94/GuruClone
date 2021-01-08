package com.luisfelipe.feature_stock.data.local.service

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.luisfelipe.feature_stock.data.local.models.BodyData
import com.luisfelipe.feature_stock.data.local.models.StockData

class StockService {

    fun getStockList(context: Context, @RawRes resourceId: Int): List<StockData> {
        val jsonString = context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
        val bodyResponse = Gson().fromJson(jsonString, BodyData::class.java)
        return bodyResponse.symbols
    }
}
