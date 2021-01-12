package com.luisfelipe.feature_stock.di

import android.content.Context
import com.luisfelipe.feature_stock.data.local.repository_impl.StockRepositoryImpl
import com.luisfelipe.feature_stock.data.local.service.StockService
import com.luisfelipe.feature_stock.domain.repositories.StockRepository
import com.luisfelipe.feature_stock.domain.usecases.GetStockListFromLocalFile
import com.luisfelipe.feature_stock.presentation.my_list.MyListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object StockModule {

    @Provides
    fun providesAdapter() = MyListAdapter()

    @Provides
    fun provideRepository(
        @ApplicationContext context: Context,
        service: StockService
    ): StockRepository =
        StockRepositoryImpl(context, service)

    @Provides
    fun provideService() = StockService()

    @Provides
    fun provideStockListFromLocalFile(repository: StockRepository) =
        GetStockListFromLocalFile(repository)
}
