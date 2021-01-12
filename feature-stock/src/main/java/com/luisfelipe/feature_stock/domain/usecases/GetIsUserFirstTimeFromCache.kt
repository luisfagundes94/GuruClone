package com.luisfelipe.feature_stock.domain.usecases

import com.luisfelipe.feature_stock.domain.repositories.SettingsRepository

class GetIsUserFirstTimeFromCache(private val repository: SettingsRepository) {
    suspend operator fun invoke() = repository.getIsUserFirstTime()
}