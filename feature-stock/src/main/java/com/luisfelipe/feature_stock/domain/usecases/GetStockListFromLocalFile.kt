package com.luisfelipe.feature_stock.domain.usecases

import com.luisfelipe.feature_stock.domain.repositories.StockRepository

class GetStockListFromLocalFile(private val repository: StockRepository) {
    suspend operator fun invoke() = repository.getStockListFromLocalFile()
}