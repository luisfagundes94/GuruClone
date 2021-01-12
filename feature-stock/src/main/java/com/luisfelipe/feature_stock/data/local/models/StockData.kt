package com.luisfelipe.feature_stock.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks")
data class StockData(
    @PrimaryKey
    val ticker: String,
    val company: String,
    val imageUrl: String,
    val variation_percent: Float,
    val price: Float
)
