package com.mercado.bitcoin.exchanges_presentation.exchangeList.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_presentation.exchangeList.viewModel.ExchangeListScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeListScreen(
    navigateToDetailsScreen: (String) -> Unit,
    viewModel: ExchangeListScreenViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle(
        initialValue = LoadingEvent.Loading
    )

    ExchangeListScreenContent(
        state = state.value,
        onEvent = viewModel::onEvent,
        onSelectExchange = { navigateToDetailsScreen(it) }
    )
}