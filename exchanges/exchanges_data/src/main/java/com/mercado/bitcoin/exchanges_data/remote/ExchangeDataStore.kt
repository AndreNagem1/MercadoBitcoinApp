package com.mercado.bitcoin.exchanges_data.remote

import com.mercado.bitcoin.core.network.BaseDataStore
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_data.entity.ExchangeListEntityItem

class ExchangeDataStore(private val exchangeListApi: ExchangeListApi) : BaseDataStore() {

    suspend fun getExchangeList(): LoadingEvent<List<ExchangeListEntityItem>> {
        return safeApiCall {
            exchangeListApi.getExchangeList()
        }
    }

    suspend fun getExchangeDetails(exchangeId: String): LoadingEvent<List<ExchangeListEntityItem>> {
        return safeApiCall {
            exchangeListApi.getExchangeDetails(
                exchangeId = exchangeId
            )
        }
    }
}