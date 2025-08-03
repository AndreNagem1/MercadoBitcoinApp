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
            if (state.loadState.refresh is LoadState.Error
                || state.loadState.append is LoadState.Error
            ) {
                return ERROR
            }

            if (state.itemCount == 0
                && state.loadState.append != LoadState.Loading
                && state.loadState.refresh != LoadState.Loading
            ) {
                return EMPTY_STATE
            }


            if (state.loadState.append == LoadState.Loading
                && state.loadState.refresh == LoadState.Loading
            ) {
                return LOADING
            }


            return SUCCESS
        }
    }
}