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
import androidx.paging.compose.LazyPagingItems
import com.mercado.bitcoin.core_ui.composables.BaseScreen
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeId
import com.mercado.bitcoin.exchanges_presentation.R
import com.mercado.bitcoin.exchanges_presentation.exchangeList.uiLogic.ExchangeListState

@Composable
fun ExchangeListScreenContent(
    pagingState: LazyPagingItems<ExchangeData>,
    onSelectExchange: (ExchangeData) -> Unit
) {
    BaseScreen(
        screenTitle = stringResource(R.string.exchange_list_screen_title),
    ) {
        LazyColumn(modifier = Modifier.padding(12.dp)) {

            val state = ExchangeListState.getState(pagingState)

            when (state) {
                ExchangeListState.SUCCESS -> {
                    items(pagingState.itemCount) { index ->
                        pagingState[index]?.let { item ->
                            Column {
                                ExchangeCard(data = item) {
                                    onSelectExchange(item)
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }

                ExchangeListState.LOADING -> {
                    item {
                        ExchangeListEmptyState()
                    }
                }

                ExchangeListState.ERROR -> {
                    item {
                        ExchangeListEmptyState()
                    }

                }

                ExchangeListState.EMPTY_STATE -> {
                    item {
                        ExchangeListEmptyState()
                    }
                }
            }
        }

    }
}