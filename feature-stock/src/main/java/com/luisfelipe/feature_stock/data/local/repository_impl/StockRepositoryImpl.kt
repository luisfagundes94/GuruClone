package com.luisfelipe.feature_stock.data.local.repository_impl

import android.content.Context
import com.luisfelipe.feature_stock.data.local.service.StockService
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.repositories.StockRepository

class StockRepositoryImpl(private val context: Context, private val stockService: StockService) :
    StockRepository {
    override suspend fun getStockListFromLocalFile(): List<Stock> {

        return listOf(
            Stock(
                companyName = "Bradesco",
                ticker = "34345",
                price = 30.5f,
                variationPercent = 5.30f
            ),
            Stock(
                companyName = "Tesla",
                ticker = "56675",
                price = 366.5f,
                variationPercent = -2.6f
            ),
            Stock(
                companyName = "SpaceX",
                ticker = "86675",
                price = 666.5f,
                variationPercent = 15.6f
            )
        )
    }

    override suspend fun getStockListFromLocalDb(): List<Stock> {
        TODO("Not yet implemented")
    }

    override suspend fun saveStockToLocalDb(stock: Stock) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteStockFromLocalDb(stock: Stock) {
        TODO("Not yet implemented")
    }
}