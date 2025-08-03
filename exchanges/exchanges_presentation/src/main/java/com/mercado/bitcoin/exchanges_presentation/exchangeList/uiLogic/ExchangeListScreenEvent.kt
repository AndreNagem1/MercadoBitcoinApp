package com.mercado.bitcoin.exchanges_presentation.exchangeList.uiLogic

sealed interface ExchangeListScreenEvent {

    data object RetryInitialCall : ExchangeListScreenEvent
}