package com.mercado.bitcoin.exchanges_presentation.exchangeList.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.mercado.bitcoin.core.exceptions.ApiException
import com.mercado.bitcoin.core_ui.composables.BaseScreen
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeId
import com.mercado.bitcoin.exchanges_presentation.R
import com.mercado.bitcoin.exchanges_presentation.exchangeList.uiLogic.ExchangeListState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExchangeListScreenContent(
    pagingState: LazyPagingItems<ExchangeData>,
    onSelectExchange: (ExchangeData) -> Unit
) {
    val state = ExchangeListState.getState(pagingState)


    BaseScreen(
        screenTitle = stringResource(R.string.exchange_list_screen_title),
    ) {
        when (state) {
            ExchangeListState.SUCCESS -> {
                LazyColumn(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.Center
                ) {

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
            }

            ExchangeListState.LOADING -> {
                ExchangeListLoading()

            }

            ExchangeListState.ERROR -> {
                ExchangeListErrorState(
                    error = ApiException.UnexpectedException(Throwable())
                )

            }

            ExchangeListState.EMPTY_STATE -> {
                ExchangeListEmptyState()
            }
        }

    }
}