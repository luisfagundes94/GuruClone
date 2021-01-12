package com.luisfelipe.feature_stock.presentation.my_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.usecases.GetIsUserFirstTimeFromCache
import com.luisfelipe.feature_stock.domain.usecases.GetStockListFromLocalFile
import com.luisfelipe.feature_stock.domain.usecases.SetIsUserFirstTimeToCache
import kotlinx.coroutines.launch

class MyListViewModel @ViewModelInject constructor(
    private val getStockListFromLocalFile: GetStockListFromLocalFile,
    private val getIsUserFirstTimeFromCache: GetIsUserFirstTimeFromCache,
    private val setIsUserFirstTimeToCache: SetIsUserFirstTimeToCache
) : ViewModel() {

    private val isUserFirstTimeLiveData = MutableLiveData<Boolean>()
    val isUserFirstTime: LiveData<Boolean> = isUserFirstTimeLiveData

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    private val stockListResultStatusLiveData = MutableLiveData<ResultStatus<List<Stock>>>()
    val stockListResultStatus: LiveData<ResultStatus<List<Stock>>> = stockListResultStatusLiveData

    fun verifyIfItIsTheUsersFirstTimeOnCache() = viewModelScope.launch {
        val firstTime = getIsUserFirstTimeFromCache()
        if (firstTime) {
            isUserFirstTimeLiveData.postValue(true)
            setIsUserFirstTimeToCache(false)
        } else isUserFirstTimeLiveData.postValue(false)
    }

    fun getStockList() = viewModelScope.launch {
        isLoadingLiveData.postValue(true)
        val stockListResultStatus = getStockListFromLocalFile()
        stockListResultStatusLiveData.postValue(stockListResultStatus)
        isLoadingLiveData.postValue(false)
    }

}
