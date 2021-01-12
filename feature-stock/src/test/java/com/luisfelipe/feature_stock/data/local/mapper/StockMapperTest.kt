package com.luisfelipe.feature_stock.data.local.mapper

import com.luisfelipe.feature_stock.data.local.models.StockData
import com.luisfelipe.feature_stock.domain.models.Company
import com.luisfelipe.feature_stock.domain.models.Stock
import com.luisfelipe.utils.FakeStockData.COMPANY_LOGO
import com.luisfelipe.utils.FakeStockData.COMPANY_NAME
import com.luisfelipe.utils.FakeStockData.PRICE
import com.luisfelipe.utils.FakeStockData.TICKER
import com.luisfelipe.utils.FakeStockData.VARIATION_PERCENT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class StockMapperTest {


    @Test
    fun `should return StockList when StockDataList is given`() {
        // Arrange
        val stockDataList = listOf(
            StockData(
                ticker = TICKER,
                price = PRICE,
                imageUrl = COMPANY_LOGO,
                variation_percent = VARIATION_PERCENT,
                company = COMPANY_NAME
            )
        )

        val stockDomainList = listOf(
            Stock(
                company = Company(COMPANY_NAME, COMPANY_LOGO),
                ticker = TICKER,
                variationPercent = VARIATION_PERCENT,
                price = PRICE
            )
        )

        // Assert
        assert(stockDomainList == StockMapper.mapDataToDomain(stockDataList))
    }
}