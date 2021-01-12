package com.luisfelipe.feature_stock.domain.models

import com.luisfelipe.utils.FakeStockData
import com.luisfelipe.utils.FakeStockData.NEGATIVE_VARIATION_PERCENT
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StockTest {

    private val stockWithNegativeVariationPercent = Stock(
        company = Company(FakeStockData.COMPANY_NAME, FakeStockData.COMPANY_LOGO),
        ticker = FakeStockData.TICKER,
        variationPercent = FakeStockData.NEGATIVE_VARIATION_PERCENT,
        price = FakeStockData.PRICE
    )

    @Test
    fun `should return a formatted variation percent given raw number`() {
        assert(stockWithNegativeVariationPercent.getFormattedVariationInPercentage() == "$NEGATIVE_VARIATION_PERCENT%")
    }
}