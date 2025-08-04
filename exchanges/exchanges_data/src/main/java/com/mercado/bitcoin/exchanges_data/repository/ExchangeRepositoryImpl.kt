package com.mercado.bitcoin.exchanges_data.repository

import android.os.Build
import androidx.annotation.RequiresApi
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

    override fun getExchangeList(currentPage: Int): Flow<LoadingEvent<List<ExchangeId>>> {
        return flowApiCall(
            apiCall = {
                dataStore.getExchangeList(
                    currentPage = currentPage
                )
            },
            transform = { response ->
                val listExchanges = mutableListOf<ExchangeId>()

                response.data.forEach { data ->
                    listExchanges.add(data.id)
                }

                listExchanges
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getExchangesInfoList(idList: List<ExchangeId>): Flow<LoadingEvent<List<ExchangeData>>> {
        return flowApiCall(
            apiCall = {
                dataStore.getExchangesInfoList(
                    idList = idList.joinToString(separator = ",")
                )
            },
            transform = { response ->
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
        )
    }

    override fun getExchangeDetails(exchangeID: String): Flow<LoadingEvent<List<CurrencyInfo>>> {
        return flowApiCall(
            apiCall = { dataStore.getExchangeDetails(exchangeId = exchangeID) },
            transform = { responseDetails ->
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
        )
    }
}