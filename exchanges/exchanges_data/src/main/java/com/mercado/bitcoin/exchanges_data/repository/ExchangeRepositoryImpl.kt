package com.mercado.bitcoin.exchanges_data.repository

import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.core.network.mapSuccess
import kotlinx.coroutines.flow.Flow
import com.mercado.bitcoin.exchanges_data.remote.ExchangeDataStore
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.model.ExchangeDetails
import com.mercado.bitcoin.exchanges_domain.model.exchangeDetailsNotMapped
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeRepository

class ExchangeRepositoryImpl(private val dataStore: ExchangeDataStore) :
    ExchangeRepository {

    override fun getExchangeList(): Flow<LoadingEvent<List<ExchangeData>>> {
        return dataStore.getExchangeList().mapSuccess { responseList ->
            val listExchanges = mutableListOf<ExchangeData>()

            responseList.forEach { data ->
                val exchangeData = ExchangeData(
                    id = data.exchangeId,
                    name = data.name,
                    volumePerDayUsd = data.volume1dayUsd
                )

                listExchanges.add(exchangeData)
            }

            listExchanges
        }
    }

    override fun getExchangeDetails(exchangeID: String): Flow<LoadingEvent<ExchangeDetails>> {
        return dataStore.getExchangeDetails(exchangeId = exchangeID).mapSuccess { data ->
            if (data.isEmpty()) {
                exchangeDetailsNotMapped()
            } else {
                ExchangeDetails(
                    exchangeId = data[0].exchangeId,
                    name = data[0].name,
                    rank = data[0].rank,
                    volume1dayUsd = data[0].volume1dayUsd,
                    volume1hrsUsd = data[0].volume1hrsUsd,
                    volume1mthUsd = data[0].volume1mthUsd,
                    website = data[0].website.orEmpty()
                )
            }
        }
    }
}