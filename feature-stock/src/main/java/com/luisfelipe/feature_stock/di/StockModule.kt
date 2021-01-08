package com.luisfelipe.feature_stock.di

import android.content.Context
import com.luisfelipe.feature_stock.data.local.repository_impl.StockRepositoryImpl
import com.luisfelipe.feature_stock.data.local.service.StockService
import com.luisfelipe.feature_stock.domain.repositories.StockRepository
import com.luisfelipe.feature_stock.domain.usecases.GetStockListFromLocalFile
import com.luisfelipe.feature_stock.presentation.my_list.StockAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
object StockModule {
    const val ADAPTER = "ADAPTER"
    const val REPOSITORY = "REPOSITORY"
    const val SERVICE = "SERVICE"
    const val GET_STOCK_LIST_FROM_LOCAL_FILE = "GET_STOCK_LIST_FROM_LOCAL_FILE"

    @Named(ADAPTER)
    @Provides
    fun providesAdapter() = StockAdapter()

    @Named(REPOSITORY)
    @Provides
    fun providesRepository(
        @ApplicationContext context: Context,
        @Named(SERVICE) service: StockService
    ): StockRepository =
        StockRepositoryImpl(context, service)

    @Named(SERVICE)
    @Provides
    fun providesService() = StockService()

    @Named(GET_STOCK_LIST_FROM_LOCAL_FILE)
    @Provides
    fun providesStockListFromLocalFile(@Named(REPOSITORY) repository: StockRepository) =
        GetStockListFromLocalFile(repository)
}
