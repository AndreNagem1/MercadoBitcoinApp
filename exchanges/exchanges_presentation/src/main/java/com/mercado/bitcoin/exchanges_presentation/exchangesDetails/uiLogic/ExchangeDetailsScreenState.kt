package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic

import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_domain.model.CurrencyInfo
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData

data class ExchangeDetailsScreenState(
    val exchangeData: ExchangeData? = null,
    val listCurrencyLoadingEvent: LoadingEvent<List<CurrencyInfo>> = LoadingEvent.Loading
)
