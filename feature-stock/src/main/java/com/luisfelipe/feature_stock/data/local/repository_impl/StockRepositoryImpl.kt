package com.luisfelipe.feature_stock.data.local.repository_impl

import android.content.Context
import com.luisfelipe.feature_stock.data.local.mapper.StockMapper
import com.luisfelipe.feature_stock.data.local.service.StockService
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.repositories.StockRepository
import com.luisfelipe.stock.R

class StockRepositoryImpl(private val context: Context, private val stockService: StockService) :
    StockRepository {
    override suspend fun getStockListFromLocalFile(): List<Stock> {
        val stockListData = stockService.getStockList(context, R.raw.json_data)
        return StockMapper.mapDataToDomain(stockListData)
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
