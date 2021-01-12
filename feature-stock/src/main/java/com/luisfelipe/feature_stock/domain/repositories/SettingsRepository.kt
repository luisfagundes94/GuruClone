package com.luisfelipe.feature_stock.domain.repositories

interface SettingsRepository {
    suspend fun getIsUserFirstTime(): Boolean
    suspend fun setIsUserFirstTime(isFirstTime: Boolean)
}