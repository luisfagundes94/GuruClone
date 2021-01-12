package com.luisfelipe.feature_stock.di

import android.content.Context
import androidx.room.Room
import com.luisfelipe.feature_stock.data.local.cache.SettingsCache
import com.luisfelipe.feature_stock.data.local.dao.StockDAO
import com.luisfelipe.feature_stock.data.local.database.StockDatabase
import com.luisfelipe.feature_stock.data.local.repository_impl.SettingsRepositoryImpl
import com.luisfelipe.feature_stock.data.local.repository_impl.StockRepositoryImpl
import com.luisfelipe.feature_stock.data.local.service.StockService
import com.luisfelipe.feature_stock.domain.repositories.SettingsRepository
import com.luisfelipe.feature_stock.domain.repositories.StockRepository
import com.luisfelipe.feature_stock.domain.usecases.*
import com.luisfelipe.feature_stock.presentation.my_list.MyListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object StockModule {

    private const val STOCK_DATABASE = "STOCK_DATABASE"

    // Adapters
    @Provides
    fun providesAdapter() = MyListAdapter()

    // Repositories
    @Provides
    fun provideStockRepository(
        @ApplicationContext context: Context,
        service: StockService,
        stockDAO: StockDAO
    ): StockRepository =
        StockRepositoryImpl(context, service, stockDAO)


    @Provides
    fun provideSettingsRepository(settingsCache: SettingsCache): SettingsRepository =
        SettingsRepositoryImpl(settingsCache)

    // Cache
    @Provides
    fun provideSettingsCache(@ApplicationContext context: Context) = SettingsCache(context)

    // Database
    @Provides
    @Singleton
    fun provideStockDatabase(@ApplicationContext context: Context): StockDatabase =
        Room.databaseBuilder(context, StockDatabase::class.java, STOCK_DATABASE)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()


    // Dao
    @Provides
    @Singleton
    fun provideStockDAO(stockDatabase: StockDatabase) = stockDatabase.stockDAO()

    // Services
    @Provides
    fun provideService() = StockService()

    // Usecases
    @Provides
    fun provideStockListFromLocalFile(repository: StockRepository) =
        GetStockListFromLocalFile(repository)

    @Provides
    fun provideGetStockListFromLocalDatabase(repository: StockRepository) =
        GetStockListFromLocalDatabase(repository)

    @Provides
    fun provideSaveStockToLocalDatabase(repository: StockRepository) =
        InsertStockToLocalDatabase(repository)

    @Provides
    fun provideDeleteStockFromLocalDatabase(repository: StockRepository) =
        DeleteStockFromLocalDatabase(repository)

    @Provides
    fun provideInsertStockListToLocalDatabase(repository: StockRepository) =
        InsertStockListToLocalDatabase(repository)

    @Provides
    fun provideGetIsUserFirstTimeFromCache(settingsRepository: SettingsRepository) =
        GetIsUserFirstTimeFromCache(settingsRepository)

    @Provides
    fun provideSetIsUserFirstTimeFromCache(settingsRepository: SettingsRepository) =
        SetIsUserFirstTimeToCache(settingsRepository)
    
}
