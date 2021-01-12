package com.luisfelipe.feature_stock.domain.usecases

import com.luisfelipe.feature_stock.domain.repositories.StockRepository

class GetStockListFromLocalDatabase(private val repository: StockRepository) {
    operator fun invoke() = repository.getStockListFromLocalDatabase()
}