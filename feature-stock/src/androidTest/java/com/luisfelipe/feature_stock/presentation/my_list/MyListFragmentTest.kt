package com.luisfelipe.feature_stock.presentation.my_list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.luisfelipe.feature_stock.R
import com.luisfelipe.feature_stock.utils.FakeStockData
import com.luisfelipe.feature_stock.utils.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MyListFragmentTest {


    private val stockList = FakeStockData.stockList
    private val stockWithNegativeVariationPercent = stockList[1]

    @Test
    fun shouldDisplayRecyclerView_whenFragmentStarts() {

        launchFragmentInHiltContainer<MyListFragment> {  }

        onView(withId(R.id.recycler_view_stocks)).check(matches(isDisplayed()))
    }
}