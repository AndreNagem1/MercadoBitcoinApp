package com.mercado.bitcoin.exchanges_presentation.exchangeList.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.mercado.bitcoin.exchanges_presentation.exchangeList.viewModel.ExchangeListScreenViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExchangeListScreen(
    navigateToDetailsScreen: () -> Unit,
    viewModel: ExchangeListScreenViewModel = koinViewModel()
) {
    val pagingState = viewModel.pagingData.collectAsLazyPagingItems()

    ExchangeListScreenContent(
        pagingState = pagingState,
        onSelectExchange = {
            viewModel.saveExchangeData(exchangeData = it)
            navigateToDetailsScreen()
        }
    )
}