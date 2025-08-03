package com.mercado.bitcoin.exchanges_data.repository

import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.core.network.flowApiCall
import com.mercado.bitcoin.exchanges_data.remote.ExchangeDataStore
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.model.ExchangeDetails
import com.mercado.bitcoin.exchanges_domain.model.exchangeDetailsNotMapped
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
                        dateLaunched = data?.dateLaunched.orEmpty()
                    )

                    mutableExchangeList.add(exchangeInfo)

                }

                mutableExchangeList.toList()
            }
        )
    }

    override fun getExchangeDetails(exchangeID: String): Flow<LoadingEvent<ExchangeDetails>> {
        return flowApiCall(
            apiCall = { dataStore.getExchangeDetails(exchangeId = exchangeID) },
            transform = { responseDetails ->
                val details = if (responseDetails.isEmpty()) {
                    exchangeDetailsNotMapped()
                } else {
                    ExchangeDetails(
                        exchangeId = responseDetails[0].exchangeId,
                        name = responseDetails[0].name,
                        rank = responseDetails[0].rank,
                        volume1dayUsd = responseDetails[0].volume1dayUsd,
                        volume1hrsUsd = responseDetails[0].volume1hrsUsd,
                        volume1mthUsd = responseDetails[0].volume1mthUsd,
                        website = responseDetails[0].website.orEmpty()
                    )
                }
                details
            }
        )
    }
}