package com.luisfelipe.feature_stock.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luisfelipe.feature_stock.data.local.dao.StockDAO
import com.luisfelipe.feature_stock.data.local.models.StockData

@Database(version = 1, entities = [StockData::class])
abstract class StockDatabase: RoomDatabase() {
    abstract fun stockDAO() : StockDAO
}