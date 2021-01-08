package com.luisfelipe.feature_stock.data.local.mapper

import com.luisfelipe.feature_stock.data.local.models.StockData
import com.luisfelipe.feature_stock.domain.models.Company
import com.luisfelipe.feature_stock.domain.models.Stock

class StockMapper {
    companion object {
        fun mapDataToDomain(stockDataList: List<StockData>): List<Stock> {
            val stockList = mutableListOf<Stock>()
            for (stockData in stockDataList) {
                val stock = Stock(
                    company = Company(stockData.company, stockData.imageUrl),
                    price = stockData.price,
                    variationPercent = stockData.variation_percent,
                    ticker = stockData.ticker
                )
                stockList.add(stock)
            }
            return stockList
        }
    }
}
