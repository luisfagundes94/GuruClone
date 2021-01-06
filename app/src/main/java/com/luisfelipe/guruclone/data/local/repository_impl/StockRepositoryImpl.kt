package com.luisfelipe.guruclone.data.local.repository_impl

import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.repositories.StockRepository

class StockRepositoryImpl: StockRepository {
    override suspend fun getStockListFromAssets(): List<Stock> {
        return emptyList()
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