package com.luisfelipe.feature_stock.data.local.models

data class StockData(
    val ticker: String,
    val company: String,
    val imageUrl: String,
    val variation_percent: Float,
    val price: Float
)
