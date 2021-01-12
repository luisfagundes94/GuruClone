package com.luisfelipe.feature_stock.di

import android.content.Context
import com.luisfelipe.feature_stock.data.local.cache.SettingsCache
import com.luisfelipe.feature_stock.data.local.repository_impl.SettingsRepositoryImpl
import com.luisfelipe.feature_stock.data.local.repository_impl.StockRepositoryImpl
import com.luisfelipe.feature_stock.data.local.service.StockService
import com.luisfelipe.feature_stock.domain.repositories.SettingsRepository
import com.luisfelipe.feature_stock.domain.repositories.StockRepository
import com.luisfelipe.feature_stock.domain.usecases.GetIsUserFirstTimeFromCache
import com.luisfelipe.feature_stock.domain.usecases.GetStockListFromLocalFile
import com.luisfelipe.feature_stock.domain.usecases.SetIsUserFirstTimeToCache
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
    fun provideStockRepository(
        @ApplicationContext context: Context,
        service: StockService
    ): StockRepository =
        StockRepositoryImpl(context, service)

    @Provides
    fun provideService() = StockService()

    @Provides
    fun provideStockListFromLocalFile(repository: StockRepository) =
        GetStockListFromLocalFile(repository)

    @Provides
    fun provideSettingsRepository(settingsCache: SettingsCache): SettingsRepository =
        SettingsRepositoryImpl(settingsCache)

    @Provides
    fun provideSettingsCache(@ApplicationContext context: Context) = SettingsCache(context)

    @Provides
    fun provideGetIsUserFirstTimeFromCache(settingsRepository: SettingsRepository) =
        GetIsUserFirstTimeFromCache(settingsRepository)

    @Provides
    fun provideSetIsUserFirstTimeToCache(settingsRepository: SettingsRepository) =
        SetIsUserFirstTimeToCache(settingsRepository)
}
