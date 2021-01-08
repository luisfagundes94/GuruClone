package com.luisfelipe.feature_stock.data.local.repository_impl

import android.content.Context
import com.luisfelipe.feature_stock.data.local.service.StockService
import io.mockk.mockk
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StockRepositoryImplTest {

    private val mockService: StockService = mockk()

    private val appContext = mockk<Context>(relaxed = true)

    private val repository = StockRepositoryImpl(appContext, mockService)
}