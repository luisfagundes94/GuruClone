package com.luisfelipe.feature_stock.data.local.repository_impl

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.luisfelipe.feature_stock.R
import com.luisfelipe.feature_stock.data.local.dao.StockDAO
import com.luisfelipe.feature_stock.data.local.mapper.StockMapper
import com.luisfelipe.feature_stock.data.local.service.StockService
import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.repositories.StockRepository
import kotlinx.coroutines.withTimeout

class StockRepositoryImpl(
    private val context: Context,
    private val stockService: StockService,
    private val stockDAO: StockDAO
) : StockRepository {

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

    override fun getStockListFromLocalDatabase(): LiveData<List<Stock>> {
        val stockDataListLiveData = stockDAO.getStockList()
        return Transformations.map(stockDataListLiveData) { stockDataList ->
            StockMapper.mapDataToDomain(stockDataList)
        }
    }

    override suspend fun deleteStockFromLocalDatabase(stock: Stock) {
        val stockData = StockMapper.mapDomainToData(stock)
        stockDAO.deleteStock(stockData)
    }

    override suspend fun saveStockListToLocalDatabase(stocks: List<Stock>) =
        stockDAO.insertStocks(StockMapper.mapDomainToData(stocks))
}
