package com.mercado.bitcoin.exchanges_presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_domain.model.CurrencyInfo
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.ui.ExchangeDetailsScreenContent
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic.ExchangeDetailsScreenState
import org.junit.Rule
import org.junit.Test

class ExchangeDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private fun fakeExchangeData(): ExchangeData {
        return ExchangeData(
            id = 1,
            name = "Binance",
            logo = "",
            description = "A leading crypto exchange",
            website = "https://binance.com",
            spotVolumeUSD = 12345.67,
            makeFee = 0.1,
            takerFee = 0.2,
            dateLaunched = null
        )
    }

    private fun fakeCryptoList(): List<CurrencyInfo> {
        return listOf(
            CurrencyInfo("Bitcoin", 40000.0),
            CurrencyInfo("Ethereum", 3000.0)
        )
    }

    @Test
    fun detailsScreen_displaysExchangeInfoCorrectly() {
        val state = ExchangeDetailsScreenState(
            exchangeData = fakeExchangeData(),
            listCurrencyLoadingEvent = LoadingEvent.Success(emptyList())
        )

        composeTestRule.setContent {
            ExchangeDetailsScreenContent(
                state = state,
                onEvent = {}
            )
        }

        composeTestRule.onNodeWithText("Binance").assertIsDisplayed()
        composeTestRule.onNodeWithText("https://binance.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("A leading crypto exchange").assertIsDisplayed()
    }

    @Test
    fun detailsScreen_displaysCryptoList() {
        val cryptoList = fakeCryptoList()
        val state = ExchangeDetailsScreenState(
            exchangeData = fakeExchangeData(),
            listCurrencyLoadingEvent = LoadingEvent.Success(cryptoList)
        )

        composeTestRule.setContent {
            ExchangeDetailsScreenContent(
                state = state,
                onEvent = {}
            )
        }

        composeTestRule.onNodeWithText("Bitcoin").assertIsDisplayed()
        composeTestRule.onNodeWithText("$40,000.00").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ethereum").assertIsDisplayed()
        composeTestRule.onNodeWithText("$3,000.00").assertIsDisplayed()
    }

    @Test
    fun detailsScreen_displaysEmptyCryptoListMessage() {
        val state = ExchangeDetailsScreenState(
            exchangeData = fakeExchangeData(),
            listCurrencyLoadingEvent = LoadingEvent.Success(emptyList())
        )

        composeTestRule.setContent {
            ExchangeDetailsScreenContent(
                state = state,
                onEvent = {}
            )
        }

        composeTestRule.onNodeWithText("Crypto list is empty").assertIsDisplayed()
    }

    @Test
    fun detailsScreen_showsLoadingState() {
        val state = ExchangeDetailsScreenState(
            exchangeData = null,
            listCurrencyLoadingEvent = LoadingEvent.Loading
        )

        composeTestRule.setContent {
            ExchangeDetailsScreenContent(
                state = state,
                onEvent = {}
            )
        }
        composeTestRule.onNode(hasTestTag("LoadingScreen")).assertIsDisplayed()
    }

    @Test
    fun detailsScreen_showsErrorState() {
        val state = ExchangeDetailsScreenState(
            exchangeData = null,
            listCurrencyLoadingEvent = LoadingEvent.Error(Throwable("Network error"))
        )

        composeTestRule.setContent {
            ExchangeDetailsScreenContent(
                state = state,
                onEvent = {}
            )
        }

        composeTestRule.onNode(hasTestTag("ErrorScreen")).assertIsDisplayed()
    }
}
