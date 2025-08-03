package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.viewmodel.ExchangeDetailsScreenViewModel

@Composable
fun ExchangeDetailsScreen(
    exchangeId: String,
    viewModel: ExchangeDetailsScreenViewModel = koinViewModel()
) {
    viewModel.exchangeId = exchangeId
    val state = viewModel.state.collectAsStateWithLifecycle()

    ExchangeDetailsScreenContent(
        state = state.value,
    )
}