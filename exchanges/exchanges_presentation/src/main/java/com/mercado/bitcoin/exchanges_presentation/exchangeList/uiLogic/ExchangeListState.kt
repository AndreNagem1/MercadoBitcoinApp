package com.mercado.bitcoin.exchanges_presentation.exchangeList.uiLogic

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData

enum class ExchangeListState {
    LOADING,
    ERROR,
    EMPTY_STATE,
    SUCCESS;

    companion object {
        fun getState(state: LazyPagingItems<ExchangeData>): ExchangeListState {
            val loadState = state.loadState

            return when {
                loadState.refresh is LoadState.Loading -> LOADING

                loadState.refresh is LoadState.Error -> ERROR

                state.itemCount == 0 && loadState.refresh is LoadState.NotLoading -> EMPTY_STATE

                else -> SUCCESS
            }
        }
    }
}