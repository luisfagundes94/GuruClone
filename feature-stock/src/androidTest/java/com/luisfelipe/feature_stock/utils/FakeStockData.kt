package com.luisfelipe.feature_stock.utils

import com.luisfelipe.feature_stock.domain.models.Company
import com.luisfelipe.feature_stock.domain.models.Stock

object FakeStockData {

    const val COMPANY_NAME = "Instagram"
    const val COMPANY_LOGO =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Instagram_logo_2016.svg/1200px-Instagram_logo_2016.svg.png"
    const val PRICE = 45450.40f
    const val VARIATION_PERCENT = 0.50f
    const val TICKER = "456631"
    const val ERROR_MESSAGE = "SE LASCOU"

    val stockList = listOf(
        Stock(
            company = Company(COMPANY_NAME, COMPANY_LOGO),
            ticker = TICKER,
            variationPercent = VARIATION_PERCENT,
            price = PRICE
        ),
        Stock(
            company = Company(COMPANY_NAME, COMPANY_LOGO),
            ticker = TICKER,
            variationPercent = VARIATION_PERCENT,
            price = PRICE
        ),
        Stock(
            company = Company(COMPANY_NAME, COMPANY_LOGO),
            ticker = TICKER,
            variationPercent = VARIATION_PERCENT,
            price = PRICE
        )
    )
}