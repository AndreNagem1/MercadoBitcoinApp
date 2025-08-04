package com.mercado.bitcoin.exchanges_domain.repository

import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

interface ExchangeSharedRepository {

    fun setCurrentExchangeData(coroutineScope: CoroutineScope, exchangeData: ExchangeData)

    fun getCurrentExchangeData(): SharedFlow<ExchangeData>
}