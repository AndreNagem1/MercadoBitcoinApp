package com.mercado.bitcoin.exchanges_data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import arrow.core.Either
import com.mercado.bitcoin.core.exceptions.ApiException
import com.mercado.bitcoin.core.extensions.convertToLocalDate
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.core.network.flowApiCall
import com.mercado.bitcoin.exchanges_data.remote.ExchangeDataStore
import com.mercado.bitcoin.exchanges_domain.model.CurrencyInfo
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeId
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow

class ExchangeRepositoryImpl(private val dataStore: ExchangeDataStore) :
    ExchangeRepository {

    override suspend fun getExchangeList(currentPage: Int): Either<ApiException, List<ExchangeId>> {
        return dataStore.getExchangeList(currentPage)
            .map { response ->
                response.data.map { it.id }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getExchangesInfoList(idList: List<ExchangeId>): Either<ApiException, List<ExchangeData>> {
        return dataStore.getExchangesInfoList(
            idList = idList.joinToString(separator = ",")
        ).map { response ->
            val mutableExchangeList = mutableListOf<ExchangeData>()

            idList.forEach { exchangeId ->
                val data = response.data[exchangeId]

                val exchangeInfo = ExchangeData(
                    id = exchangeId,
                    name = data?.name,
                    logo = data?.logo.orEmpty(),
                    spotVolumeUSD = data?.spotVolumeUsd ?: 0.0,
                    dateLaunched = data?.dateLaunched?.convertToLocalDate(),
                    description = data?.description.orEmpty(),
                    makeFee = data?.makerFee,
                    takerFee = data?.takerFee,
                    website = data?.urls?.website?.firstOrNull(),
                )

                mutableExchangeList.add(exchangeInfo)

            }

            mutableExchangeList.toList()
        }

    }

    override suspend fun getExchangeDetails(exchangeID: String): Either<ApiException, List<CurrencyInfo>> {
        return dataStore.getExchangeDetails(exchangeId = exchangeID).map { responseDetails ->
            val currencyList = mutableListOf<CurrencyInfo>()

            responseDetails.data.forEach {
                val currencyInfo = CurrencyInfo(
                    name = it.currency.name,
                    priceUSD = it.currency.priceUsd
                )
                currencyList.add(currencyInfo)
            }

            currencyList
        }
    }
}