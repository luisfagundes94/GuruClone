package com.luisfelipe.feature_stock.domain.usecases

import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.repositories.StockRepository

class DeleteStockFromLocalDatabase(private val repository: StockRepository) {
    suspend operator fun invoke(stock: Stock) = repository.deleteStockFromLocalDatabase(stock)
}