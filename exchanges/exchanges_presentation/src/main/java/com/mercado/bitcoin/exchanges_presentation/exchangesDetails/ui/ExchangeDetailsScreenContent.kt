package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.mercado.bitcoin.core.extensions.toDollarCurrency
import com.mercado.bitcoin.core.network.getErrorApiExceptionOrNull
import com.mercado.bitcoin.core.network.getSuccessDataOrNull
import com.mercado.bitcoin.core.network.isError
import com.mercado.bitcoin.core.network.isLoading
import com.mercado.bitcoin.core_ui.composables.BaseScreen
import com.mercado.bitcoin.exchanges_presentation.R
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic.ExchangeDetailsScreenEvent
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic.ExchangeDetailsScreenState

@Composable
fun ExchangeDetailsScreenContent(
    state: ExchangeDetailsScreenState,
    onEvent: (ExchangeDetailsScreenEvent) -> Unit,
) {
    BaseScreen(
        screenTitle = stringResource(R.string.exchange_details_screen_title),
        isLoading = state.listCurrencyLoadingEvent.isLoading(),
        isError = state.listCurrencyLoadingEvent.isError(),
        error = state.listCurrencyLoadingEvent.getErrorApiExceptionOrNull(),
        retryInitialCall = { onEvent(ExchangeDetailsScreenEvent.RetryInitialCall) }
    ) {
        LazyColumn {
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val data = state.exchangeData

                    Text(text = data?.name ?: stringResource(R.string.exchange_no_name))
                    Text(text = data?.website.orEmpty())
                    Text(text = data?.spotVolumeUSD?.toDollarCurrency() ?: "Not informed")
                    Text(text = data?.makeFee?.toDollarCurrency() ?: "Not informed")
                    Text(text = data?.takerFee?.toDollarCurrency() ?: "Not informed")
                }
            }

            state.listCurrencyLoadingEvent.getSuccessDataOrNull()?.let { currencyList ->
                items(currencyList.size) { index ->
                    val currency = currencyList[index]
                    Text(text = currency.name)
                    Text(text = currency.priceUSD.toDollarCurrency())
                }
            }
        }

    }
}