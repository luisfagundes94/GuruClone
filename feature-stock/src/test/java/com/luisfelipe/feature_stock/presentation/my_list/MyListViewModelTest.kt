package com.luisfelipe.feature_stock.presentation.my_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.luisfelipe.feature_stock.domain.enums.ResultStatus
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.feature_stock.domain.usecases.*
import com.luisfelipe.utils.CoroutineRule
import com.luisfelipe.utils.FakeStockData
import com.luisfelipe.utils.FakeStockData.ERROR_MESSAGE
import com.luisfelipe.utils.getOrAwaitValue
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MyListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val stockListLiveData: MutableLiveData<List<Stock>> = mockk()

    private val getStockListFromLocalFile: GetStockListFromLocalFile = mockk()
    private val insertStockToLocalDatabase: InsertStockToLocalDatabase = mockk()
    private val getIsUserFirstTimeFromCache: GetIsUserFirstTimeFromCache = mockk()
    private val setIsUserFirstTimeFromCache: SetIsUserFirstTimeToCache = mockk()
    private val getStockListFromLocalDatabase: GetStockListFromLocalDatabase = mockk()
    private val deleteStockFromLocalDatabase: DeleteStockFromLocalDatabase = mockk()
    private val insertStockListToLocalDatabase: InsertStockListToLocalDatabase = mockk()

    private lateinit var viewModel: MyListViewModel

    private val mockedStockList = FakeStockData.stockList

    @Before
    fun setUp() {
        coEvery { getStockListFromLocalDatabase() } returns stockListLiveData

        viewModel = spyk(
            MyListViewModel(
                getStockListFromLocalFile,
                getIsUserFirstTimeFromCache,
                setIsUserFirstTimeFromCache,
                getStockListFromLocalDatabase,
                deleteStockFromLocalDatabase,
                insertStockListToLocalDatabase,
                insertStockToLocalDatabase
            )
        )
    }

    @Test
    fun `verify state when getStockListFromLocalFile returns an empty list`() {
        // Arrange
        coEvery { getStockListFromLocalFile() } returns ResultStatus.Empty

        // Act
        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.getStockListFromFile()
        }

        // Assert
        val stockListResultStatus = viewModel.stockListResultStatus.getOrAwaitValue()
        assert(stockListResultStatus == ResultStatus.Empty)
    }

    @Test
    fun `verify state when getStockListFromLocalFile returns a list`() {
        // Arrange
        coEvery { getStockListFromLocalFile() } returns ResultStatus.Success(mockedStockList)

        // Act
        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.getStockListFromFile()
        }

        // Assert
        val stockListResultStatus = viewModel.stockListResultStatus.getOrAwaitValue()
        assert(stockListResultStatus == ResultStatus.Success(mockedStockList))
    }

    @Test
    fun `verify state when getStockListFromLocalFile returns an error`() {
        // Arrange
        coEvery { getStockListFromLocalFile() } returns ResultStatus.Error(ERROR_MESSAGE)

        // Act
        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.getStockListFromFile()
        }

        // Assert
        val stockListResultStatus = viewModel.stockListResultStatus.getOrAwaitValue()
        assert(stockListResultStatus == ResultStatus.Error(ERROR_MESSAGE))
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
        val isUserFirstTime = viewModel.isUserFirstTime.getOrAwaitValue()
        assert(isUserFirstTime)
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
        val isUserFirstTime = viewModel.isUserFirstTime.getOrAwaitValue()
        assert(!isUserFirstTime)
    }

    @Test
    fun `verify if isLoadingValue is false when getStockListFromFile returns a stock list`() {
        // Arrange
        coEvery { getStockListFromLocalFile() } returns ResultStatus.Success(mockedStockList)

        // Act
        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.getStockListFromFile()
        }

        // Assert
        val isLoading = viewModel.isLoading.getOrAwaitValue()
        assert(!isLoading)
    }


}