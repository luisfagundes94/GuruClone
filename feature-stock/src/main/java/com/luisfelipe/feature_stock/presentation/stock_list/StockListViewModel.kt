package com.luisfelipe.feature_stock.presentation.stock_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfelipe.feature_stock.di.StockModule.GET_STOCK_LIST_FROM_LOCAL_FILE
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.usecases.GetStockListFromLocalFile
import javax.inject.Named
import kotlinx.coroutines.launch

class StockListViewModel @ViewModelInject constructor(
    @Named(GET_STOCK_LIST_FROM_LOCAL_FILE)
    private val getStockListFromLocalFile: GetStockListFromLocalFile
) : ViewModel() {

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    private val stocksLiveData = MutableLiveData<List<Stock>>()
    val stocks: LiveData<List<Stock>> = stocksLiveData

    fun getStockList() = viewModelScope.launch {
        isLoadingLiveData.postValue(true)
        val stocks = getStockListFromLocalFile()
        stocksLiveData.postValue(stocks)
    }
}
