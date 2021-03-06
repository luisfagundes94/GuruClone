package com.luisfelipe.feature_stock.domain.models

data class Stock(
    val company: Company,
    val ticker: String,
    val variationPercent: Float,
    val price: Float,
    var isSelected: Boolean = false
) {
    fun getFormattedVariationInPercentage(): String {
        return if (variationPercent > 0) "+$variationPercent%"
        else "$variationPercent%"
    }
}
