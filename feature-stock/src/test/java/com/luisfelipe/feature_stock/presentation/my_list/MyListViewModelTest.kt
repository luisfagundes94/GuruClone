package com.luisfelipe.feature_stock.presentation.my_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.domain.usecases.GetIsUserFirstTimeFromCache
import com.luisfelipe.feature_stock.domain.usecases.GetStockListFromLocalFile
import com.luisfelipe.feature_stock.domain.usecases.SetIsUserFirstTimeToCache
import com.luisfelipe.utils.CoroutineRule
import com.luisfelipe.utils.FakeStockData
import com.luisfelipe.utils.FakeStockData.ERROR_MESSAGE
import com.luisfelipe.utils.getOrAwaitValue
import io.mockk.*
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
    val coroutinesTestRule = CoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val getStockListFromLocalFile: GetStockListFromLocalFile = mockk()
    private val getIsUserFirstTimeFromCache: GetIsUserFirstTimeFromCache = mockk()
    private val setIsUserFirstTimeFromCache: SetIsUserFirstTimeToCache = mockk()

    private val viewModel = spyk(
        MyListViewModel(
            getStockListFromLocalFile,
            getIsUserFirstTimeFromCache,
            setIsUserFirstTimeFromCache
        )
    )

    private val mockedStockList = FakeStockData.stockList

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

    @Test
    fun `verify state when getIsUserFirsTimeFromCache returns true`() {
        // Arrange
        coEvery { getIsUserFirstTimeFromCache() } returns true
        coEvery { setIsUserFirstTimeFromCache(false) } just runs

        // Act
        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.verifyIfItIsTheUsersFirstTimeOnCache()
        }

        // Assert
        val value = viewModel.isUserFirstTime.getOrAwaitValue()
        assert(value)
    }

    @Test
    fun `verify state when getIsUserFirstTimeFrom cache returns false`() {
        // Arrange
        coEvery { getIsUserFirstTimeFromCache() } returns false

        // Act
        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.verifyIfItIsTheUsersFirstTimeOnCache()
        }

        // Assert
        val value = viewModel.isUserFirstTime.getOrAwaitValue()
        assert(!value)
    }


}