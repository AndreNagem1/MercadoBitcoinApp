package com.mercado.bitcoin.exchanges_domain.repository

import com.mercado.bitcoin.core.network.LoadingEvent
import kotlinx.coroutines.flow.Flow
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.model.ExchangeDetails


interface ExchangeRepository {
    fun getExchangeList(): Flow<LoadingEvent<List<ExchangeData>>>
    fun getExchangeDetails(exchangeID : String): Flow<LoadingEvent<ExchangeDetails>>
}