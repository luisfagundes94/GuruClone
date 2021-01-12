package com.luisfelipe.feature_stock.data.local.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.firstOrNull

class SettingsCache(private val context: Context) {

    companion object {
        const val DATA_STORE_NAME = "SETTINGS"

        const val IS_USER_FIRST_TIME_KEY = "ITS_USER_FIRST_TIME"
        const val ITS_USER_FIRST_TIME_DEFAULT_VALUE = true
    }

    private val dataStore = context.createDataStore(name = DATA_STORE_NAME)
    private val dataStoreKey = preferencesKey<Boolean>(IS_USER_FIRST_TIME_KEY)

    suspend fun setIsUserFirstTime(isFirstTime: Boolean) {
        dataStore.edit { preferences ->
            preferences[dataStoreKey] = isFirstTime
        }
    }

    suspend fun getIsUserFirstTime(): Boolean {
        val preferences = dataStore.data.firstOrNull()
        val isUserFirstTime = preferences?.get(dataStoreKey)
        return isUserFirstTime ?: ITS_USER_FIRST_TIME_DEFAULT_VALUE
    }

}