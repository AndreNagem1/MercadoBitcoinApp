package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeId
import org.koin.androidx.compose.koinViewModel
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.viewmodel.ExchangeDetailsScreenViewModel

@Composable
fun ExchangeDetailsScreen(
    exchangeId: ExchangeId,
    viewModel: ExchangeDetailsScreenViewModel = koinViewModel()
) {
    viewModel.exchangeId = exchangeId
    val state = viewModel.state.collectAsStateWithLifecycle()

    ExchangeDetailsScreenContent(
        state = state.value,
        onEvent = viewModel::onEvent
    )
}