package com.luisfelipe.feature_stock.domain.models

data class Stock(
    val companyName: String,
    val ticker: String,
    val variationPercent: Float,
    val price: Float,
) {
    fun getFormattedVariationInPercentage(): String {
        return if (variationPercent > 0) "+${variationPercent}%"
        else "${variationPercent}%"
    }
}
