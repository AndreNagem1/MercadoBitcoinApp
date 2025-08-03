package com.mercado.bitcoin.exchanges_domain.repository

import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import kotlinx.coroutines.flow.Flow
import com.mercado.bitcoin.exchanges_domain.model.ExchangeDetails

typealias ExchangeId = Int

interface ExchangeRepository {
    fun getExchangeList(currentPage: Int): Flow<LoadingEvent<List<ExchangeId>>>

    fun getExchangesInfoList(idList : List<ExchangeId>) :Flow<LoadingEvent<List<ExchangeData>>>

    fun getExchangeDetails(exchangeID : String): Flow<LoadingEvent<ExchangeDetails>>
}