package com.luisfelipe.feature_stock.domain.repositories

import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.domain.models.Stock

interface StockRepository {
    suspend fun getStockListFromLocalFile(): ResultStatus<List<Stock>>
}
