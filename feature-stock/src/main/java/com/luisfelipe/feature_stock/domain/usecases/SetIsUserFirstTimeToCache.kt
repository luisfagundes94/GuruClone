package com.luisfelipe.feature_stock.domain.usecases

import com.luisfelipe.feature_stock.domain.repositories.SettingsRepository

class SetIsUserFirstTimeToCache(private val repository: SettingsRepository) {
    suspend operator fun invoke(isFirstTime: Boolean) = repository.setIsUserFirstTime(isFirstTime)
}