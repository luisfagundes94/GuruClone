package com.luisfelipe.feature_stock.data.local.repository_impl

import com.luisfelipe.feature_stock.data.local.cache.SettingsCache
import com.luisfelipe.feature_stock.domain.repositories.SettingsRepository

class SettingsRepositoryImpl(private val settingsCache: SettingsCache) : SettingsRepository {

    override suspend fun getIsUserFirstTime() = settingsCache.getIsUserFirstTime()

    override suspend fun setIsUserFirstTime(isFirstTime: Boolean) =
        settingsCache.setIsUserFirstTime(isFirstTime)

}