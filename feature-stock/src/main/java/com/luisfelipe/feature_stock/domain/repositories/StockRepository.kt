package com.luisfelipe.feature_stock.domain.repositories

import com.luisfelipe.feature_stock.domain.models.Stock

interface StockRepository {
    suspend fun getStockListFromLocalFile(): List<Stock>
    suspend fun getStockListFromLocalDb(): List<Stock>
    suspend fun saveStockToLocalDb(stock: Stock)
    suspend fun deleteStockFromLocalDb(stock: Stock)
}
