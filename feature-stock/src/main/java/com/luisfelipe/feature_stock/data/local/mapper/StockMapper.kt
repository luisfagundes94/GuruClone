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

        fun mapDomainToData(stockList: List<Stock>): List<StockData> {
            val stockListData = mutableListOf<StockData>()
            for (stock in stockList) {
                val stockData = StockData(
                    ticker = stock.ticker,
                    price = stock.price,
                    company = stock.company.name,
                    imageUrl = stock.company.logo,
                    variation_percent = stock.variationPercent
                )
                stockListData.add(stockData)
            }
            return stockListData
        }

        fun mapDomainToData(stock: Stock): StockData {
            return StockData(
                company = stock.company.name,
                imageUrl = stock.company.logo,
                price = stock.price,
                ticker = stock.ticker,
                variation_percent = stock.variationPercent
            )
        }
    }
}
