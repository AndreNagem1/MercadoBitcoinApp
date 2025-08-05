package com.mercado.bitcoin.exchanges_data.remote

import arrow.core.Either
import com.mercado.bitcoin.core.exceptions.ApiException
import com.mercado.bitcoin.core.network.BaseDataStore
import com.mercado.bitcoin.exchanges_data.entity.ExchangeDetailsEntity
import com.mercado.bitcoin.exchanges_data.entity.ExchangeInfoEntity
import com.mercado.bitcoin.exchanges_data.entity.ExchangeMapEntity

class ExchangeDataStore(private val exchangeListApi: ExchangeListApi) : BaseDataStore() {

    suspend fun getExchangeList(currentPage: Int): Either<ApiException ,ExchangeMapEntity> {
        return safeApiCall {
            exchangeListApi.getExchangeList(
                start = currentPage
            )
        }
    }

    suspend fun getExchangesInfoList(idList: String): Either<ApiException ,ExchangeInfoEntity> {
        return safeApiCall {
            exchangeListApi.getExchangeInfoList(
                idList = idList
            )
        }
    }

    suspend fun getExchangeDetails(exchangeId: String): Either<ApiException ,ExchangeDetailsEntity> {
        return safeApiCall {
            exchangeListApi.getExchangeDetails(
                exchangeId = exchangeId
            )
        }
    }
}