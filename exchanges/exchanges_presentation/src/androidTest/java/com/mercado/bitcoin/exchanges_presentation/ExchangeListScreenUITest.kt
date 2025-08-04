package com.mercado.bitcoin.exchanges_presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_presentation.exchangeList.ui.ExchangeListScreenContent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ExchangeListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private fun generateTestData(): List<ExchangeData> {
        return listOf(
            ExchangeData(
                120,
                "Binance",
                "",
                logo = "",
                spotVolumeUSD = 22.23,
                dateLaunched = null,
                makeFee = null,
                takerFee = null,
                website = null
            ),
            ExchangeData(
                32,
                "Coinbase",
                "",
                logo = "",
                spotVolumeUSD = 22.23,
                dateLaunched = null,
                makeFee = null,
                takerFee = null,
                website = null
            )
        )
    }


    @Test
    fun exchangeList_displaysItemsCorrectly() = runTest {

        composeTestRule.setContent {
            val pagingItems = fakeLazyPagingItems(generateTestData())

            ExchangeListScreenContent(
                pagingState = pagingItems,
                onSelectExchange = {}
            )
        }

        composeTestRule.onNodeWithText("Binance").assertIsDisplayed()
        composeTestRule.onNodeWithText("Coinbase").assertIsDisplayed()
    }

    @Test
    fun exchangeList_showsEmptyState() = runTest {

        composeTestRule.setContent {
            val pagingItems = fakeLazyPagingItems(emptyList())

            ExchangeListScreenContent(
                pagingState = pagingItems,
                onSelectExchange = {}
            )
        }

        composeTestRule.onNodeWithText("Empty State")
            .assertIsDisplayed()
    }
}
