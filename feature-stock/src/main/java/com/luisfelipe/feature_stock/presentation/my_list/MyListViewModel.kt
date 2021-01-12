package com.luisfelipe.feature_stock.presentation.my_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.usecases.*
import kotlinx.coroutines.launch

class MyListViewModel @ViewModelInject constructor(
    private val getStockListFromLocalFile: GetStockListFromLocalFile,
    private val getIsUserFirstTimeFromCache: GetIsUserFirstTimeFromCache,
    private val setIsUserFirstTimeToCache: SetIsUserFirstTimeToCache,
    private val getStockListFromLocalDatabase: GetStockListFromLocalDatabase,
    private val deleteStockFromLocalDatabase: DeleteStockFromLocalDatabase,
    private val insertStockListToLocalDatabase: InsertStockListToLocalDatabase
) : ViewModel() {

    private val isUserFirstTimeLiveData = MutableLiveData<Boolean>()
    val isUserFirstTime: LiveData<Boolean> = isUserFirstTimeLiveData

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    private val stockListResultStatusLiveData = MutableLiveData<ResultStatus<List<Stock>>>()
    val stockListResultStatus: LiveData<ResultStatus<List<Stock>>> = stockListResultStatusLiveData

    val stockListFromLocalDb: LiveData<List<Stock>> = getStockListFromLocalDatabase()

    fun verifyIfItIsTheUsersFirstTimeOnCache() = viewModelScope.launch {
        val firstTime = getIsUserFirstTimeFromCache()
        if (firstTime) {
            setIsUserFirstTimeToCache(false)
            isUserFirstTimeLiveData.postValue(true)
        } else isUserFirstTimeLiveData.postValue(false)
    }

    fun getStockListFromFile() = viewModelScope.launch {
        isLoadingLiveData.postValue(true)
        val stockListResultStatus = getStockListFromLocalFile()
        stockListResultStatusLiveData.postValue(stockListResultStatus)
        isLoadingLiveData.postValue(false)
    }

    fun deleteStock(stock: Stock) = viewModelScope.launch {
        deleteStockFromLocalDatabase(stock)
    }

    fun saveStockListToLocalDb(stockList: List<Stock>) = viewModelScope.launch {
        insertStockListToLocalDatabase(stockList)
    }

}
