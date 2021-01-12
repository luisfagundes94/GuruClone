package com.luisfelipe.feature_stock.data.local.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey

import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.first

class SettingsCache(private val context: Context) {

    companion object {
        const val IS_USER_FIRST_TIME_KEY = "ITS_USER_FIRST_TIME"
        const val ITS_USER_FIRST_TIME_DEFAULT_VALUE = true
        const val DATA_STORE_NAME = "SETTINGS"
    }

    private val dataStore = context.createDataStore(name = DATA_STORE_NAME)

    suspend fun setIsUserFirstTime(isFirstTime: Boolean) {
        val dataStoreKey = preferencesKey<Boolean>(IS_USER_FIRST_TIME_KEY)
        dataStore.edit { settings ->
            settings[dataStoreKey] = isFirstTime
        }
    }

    suspend fun getIsUserFirstTime(): Boolean {
        val dataStoreKey = preferencesKey<Boolean>(IS_USER_FIRST_TIME_KEY)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey] == null
    }
}