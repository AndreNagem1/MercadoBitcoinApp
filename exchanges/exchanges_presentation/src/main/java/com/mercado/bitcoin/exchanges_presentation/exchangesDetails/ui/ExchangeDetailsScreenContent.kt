package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mercado.bitcoin.core.extensions.toDollarCurrency
import com.mercado.bitcoin.core_ui.composables.BaseScreen
import com.mercado.bitcoin.exchanges_presentation.R
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.core.network.getErrorApiExceptionOrNull
import com.mercado.bitcoin.core.network.getSuccessDataOrNull
import com.mercado.bitcoin.core.network.isError
import com.mercado.bitcoin.core.network.isLoading
import com.mercado.bitcoin.exchanges_domain.model.ExchangeDetails
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic.ExchangeDetailsScreenEvent

@Composable
fun ExchangeDetailsScreenContent(
    state: LoadingEvent<ExchangeDetails>,
    onEvent: (ExchangeDetailsScreenEvent) -> Unit,
) {
    BaseScreen(
        screenTitle = stringResource(R.string.exchange_details_screen_title),
        isLoading = state.isLoading(),
        isError = state.isError(),
        error = state.getErrorApiExceptionOrNull(),
        retryInitialCall = { onEvent(ExchangeDetailsScreenEvent.RetryInitialCall) }
    ) {
        state.getSuccessDataOrNull()?.let { data ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = data.exchangeId)
                Text(text = data.name ?: stringResource(R.string.exchange_no_name))
                Text(text = data.website)
                Text(text = data.volume1dayUsd.toDollarCurrency())
                Text(text = data.volume1hrsUsd.toDollarCurrency())
                Text(text = data.volume1mthUsd.toDollarCurrency())
            }
        }
    }
}