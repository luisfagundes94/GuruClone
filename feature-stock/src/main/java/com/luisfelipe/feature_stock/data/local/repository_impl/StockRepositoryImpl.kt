package com.luisfelipe.feature_stock.data.local.repository_impl

import android.content.Context
import com.luisfelipe.feature_stock.R
import com.luisfelipe.feature_stock.data.local.mapper.StockMapper
import com.luisfelipe.feature_stock.data.local.service.StockService
import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.repositories.StockRepository
import kotlinx.coroutines.withTimeout

class StockRepositoryImpl(private val context: Context, private val stockService: StockService) :
    StockRepository {

    companion object {
        const val REQUEST_TIMEOUT = 5000L
    }

    override suspend fun getStockListFromLocalFile(): ResultStatus<List<Stock>> {
        return withTimeout(REQUEST_TIMEOUT) {
            try {
                val stockListData = stockService.getStockList(context, R.raw.json_data)
                val stockList = StockMapper.mapDataToDomain(stockListData)

                if (stockList.isEmpty()) return@withTimeout ResultStatus.Empty
                else return@withTimeout ResultStatus.Success(stockList)

            } catch (exception: Exception) {
                return@withTimeout ResultStatus.Error(exception.message.toString())
            }
        }
    }
}
