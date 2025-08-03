package com.mercado.bitcoin.exchanges_presentation.exchangeList.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mercado.bitcoin.core_ui.composables.BaseScreen
import com.mercado.bitcoin.exchanges_presentation.R
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.core.network.getSuccessDataOrNull
import com.mercado.bitcoin.core.network.isError
import com.mercado.bitcoin.core.network.isLoading
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData

@Composable
fun ExchangeListScreenContent(
    state: LoadingEvent<List<ExchangeData>>,
    onSelectExchange: (String) -> Unit
) {
    BaseScreen(
        screenTitle = stringResource(R.string.exchange_list_screen_title),
        isLoading = state.isLoading(),
        isError = state.isError(),
    ) {
        state.getSuccessDataOrNull()?.let { exchangeList ->
            LazyColumn(modifier = Modifier.padding(12.dp)) {
                items(exchangeList.size) { index ->
                    val data = exchangeList[index]
                    Column {
                        ExchangeCard(data = data) {
                            onSelectExchange(data.id)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}