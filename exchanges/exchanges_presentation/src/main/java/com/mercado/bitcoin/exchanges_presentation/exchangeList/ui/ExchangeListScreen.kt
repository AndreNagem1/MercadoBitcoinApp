package com.mercado.bitcoin.exchanges_presentation.exchangeList.ui

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeId
import com.mercado.bitcoin.exchanges_presentation.exchangeList.viewModel.ExchangeListScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeListScreen(
    navigateToDetailsScreen: (ExchangeId) -> Unit,
    viewModel: ExchangeListScreenViewModel = koinViewModel()
) {
    val pagingState = viewModel.pagingData.collectAsLazyPagingItems()

    ExchangeListScreenContent(
        pagingState = pagingState,
        onSelectExchange = { navigateToDetailsScreen(it) }
    )
}