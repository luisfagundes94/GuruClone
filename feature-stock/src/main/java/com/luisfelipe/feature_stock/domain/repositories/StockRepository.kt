package com.luisfelipe.feature_stock.domain.repositories

import androidx.lifecycle.LiveData
import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.domain.models.Stock

interface StockRepository {
    suspend fun getStockListFromLocalFile(): ResultStatus<List<Stock>>
    fun getStockListFromLocalDatabase(): LiveData<List<Stock>>
    suspend fun saveStockToLocalDatabase(stock: Stock)
    suspend fun deleteStockFromLocalDatabase(stock: Stock)
    suspend fun saveStockListToLocalDatabase(stocks: List<Stock>)
}
