package com.mercado.bitcoin.exchanges_presentation.exchangeList.useCase

import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetExchangeListUseCase(private val repository: ExchangeRepository) {

    operator fun invoke(currentPage: Int): Flow<LoadingEvent<List<ExchangeData>>> = flow {
        emit(LoadingEvent.Loading)

        repository.getExchangeList(currentPage).collect { mapEvent ->
            when (mapEvent) {
                is LoadingEvent.Loading -> emit(LoadingEvent.Loading)

                is LoadingEvent.Error -> emit(LoadingEvent.Error(mapEvent.exception))

                is LoadingEvent.Success -> {
                    val exchangeIdList = mapEvent.data.map { it }

                    if (exchangeIdList.isEmpty()) {
                        emit(LoadingEvent.Success(emptyList()))
                        return@collect
                    }

                    repository.getExchangesInfoList(exchangeIdList).collect { infoEvent ->
                        emit(infoEvent)
                    }
                }


                LoadingEvent.Idle -> {}
            }
        }
    }.flowOn(Dispatchers.IO)
}

