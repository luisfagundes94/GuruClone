package com.luisfelipe.feature_stock.di

import com.luisfelipe.feature_stock.presentation.my_list.StockAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object StockModule {

    @Provides
    fun providesAdapter() = StockAdapter()
}