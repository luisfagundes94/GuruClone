package com.luisfelipe.feature_stock.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.luisfelipe.feature_stock.data.local.models.StockData

@Dao
interface StockDAO {

    @Query("SELECT * FROM stocks")
    fun getStockList(): LiveData<List<StockData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStocks(stocks: List<StockData>)

    @Delete
    fun deleteStock(stockData: StockData)

}