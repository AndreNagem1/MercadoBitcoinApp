package com.mercado.bitcoin.exchanges_data.remote

import com.mercado.bitcoin.core.network.BaseDataStore
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_data.entity.ExchangeInfoEntity
import com.mercado.bitcoin.exchanges_data.entity.ExchangeListEntityItem
import com.mercado.bitcoin.exchanges_data.entity.ExchangeMapEntity
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeId

class ExchangeDataStore(private val exchangeListApi: ExchangeListApi) : BaseDataStore() {

    suspend fun getExchangeList(currentPage: Int): LoadingEvent<ExchangeMapEntity> {
        return safeApiCall {
            exchangeListApi.getExchangeList(
                start = currentPage
            )
        }
    }

    suspend fun getExchangesInfoList(idList: String): LoadingEvent<ExchangeInfoEntity> {
        return safeApiCall {
            exchangeListApi.getExchangeInfoList(
                idList = idList
            )
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