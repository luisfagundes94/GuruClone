package com.luisfelipe.feature_stock.presentation.my_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.usecases.GetStockListFromLocalFile
import kotlinx.coroutines.launch

class MyListViewModel @ViewModelInject constructor(
    private val getStockListFromLocalFile: GetStockListFromLocalFile
) : ViewModel() {

    private val stocksLiveData = MutableLiveData<List<Stock>>()
    val stocks: LiveData<List<Stock>> = stocksLiveData

    private val stockListResultStatusLiveData = MutableLiveData<ResultStatus<List<Stock>>>()
    val stockListResultStatus: LiveData<ResultStatus<List<Stock>>> = stockListResultStatusLiveData

    fun getStockList() = viewModelScope.launch {
        val stockListResultStatus = getStockListFromLocalFile()
        stockListResultStatusLiveData.postValue(stockListResultStatus)
    }

}
