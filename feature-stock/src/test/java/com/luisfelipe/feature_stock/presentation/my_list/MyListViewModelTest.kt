package com.luisfelipe.feature_stock.presentation.my_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.domain.models.Company
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.usecases.GetStockListFromLocalFile
import com.luisfelipe.utils.CoroutineRule
import com.luisfelipe.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MyListViewModelTest{

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val getStockListFromLocalFile: GetStockListFromLocalFile = mockk()

    private val viewModel = spyk(MyListViewModel(getStockListFromLocalFile))

    private val mockedStockList = listOf(
        Stock(
            company = Company(COMPANY_NAME, COMPANY_LOGO),
            ticker = TICKER,
            variationPercent = VARIATION_PERCENT,
            price = PRICE
        )
    )

    @Test
    fun `verify state when getStockListFromLocalFile returns an empty list`() {
        // Arrange
        coEvery { getStockListFromLocalFile() } returns ResultStatus.Empty

        // Act
        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.getStockList()
        }

        // Assert
        val value = viewModel.stockListResultStatus.getOrAwaitValue()
        assert(value == ResultStatus.Empty)
    }

    @Test
    fun `verify state when getStockListFromLocalFile returns a list`() {
        // Arrange
        coEvery { getStockListFromLocalFile() } returns ResultStatus.Success(mockedStockList)

        // Act
        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.getStockList()
        }

        // Assert
        val value = viewModel.stockListResultStatus.getOrAwaitValue()
        assert(value == ResultStatus.Success(mockedStockList))
    }

    @Test
    fun `verify state when getStockListFromLocalFile returns an error`() {
        // Arrange
        coEvery { getStockListFromLocalFile() } returns ResultStatus.Error(ERROR_MESSAGE)

        // Act
        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.getStockList()
        }

        // Assert
        val value = viewModel.stockListResultStatus.getOrAwaitValue()
        assert(value == ResultStatus.Error(ERROR_MESSAGE))
    }

    companion object {
        const val COMPANY_NAME = "Instagram"
        const val COMPANY_LOGO = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Instagram_logo_2016.svg/1200px-Instagram_logo_2016.svg.png"
        const val PRICE = 45450.40f
        const val VARIATION_PERCENT = 0.50f
        const val TICKER = "456631"
        const val ERROR_MESSAGE = "SE LASCOU"
    }
}