package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.viewmodel.ExchangeDetailsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeDetailsScreen(
    viewModel: ExchangeDetailsScreenViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    ExchangeDetailsScreenContent(
        state = state.value,
        onEvent = viewModel::onEvent
    )
}