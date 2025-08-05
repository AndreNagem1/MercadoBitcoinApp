package com.mercado.bitcoin.exchanges_domain.repository

import arrow.core.Either
import com.mercado.bitcoin.core.exceptions.ApiException
import com.mercado.bitcoin.exchanges_domain.model.CurrencyInfo
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData

typealias ExchangeId = Int

interface ExchangeRepository {
    suspend fun getExchangeList(currentPage: Int): Either<ApiException, List<ExchangeId>>

    suspend fun getExchangesInfoList(idList : List<ExchangeId>) :Either<ApiException,List<ExchangeData>>

    suspend fun getExchangeDetails(exchangeID : String): Either<ApiException, List<CurrencyInfo>>
}