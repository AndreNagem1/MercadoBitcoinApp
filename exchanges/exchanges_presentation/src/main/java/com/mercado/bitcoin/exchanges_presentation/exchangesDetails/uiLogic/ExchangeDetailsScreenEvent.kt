package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic

sealed interface ExchangeDetailsScreenEvent {

    data object RetryInitialCall : ExchangeDetailsScreenEvent
}