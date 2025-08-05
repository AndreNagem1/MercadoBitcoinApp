package com.mercado.bitcoin.exchanges_presentation.exchangeList.useCase

import arrow.core.Either
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

        when (val result = repository.getExchangeList(currentPage)) {
            is Either.Left -> emit(LoadingEvent.Error(result.value))
            is Either.Right -> {
                val exchangeIdList = result.value

                if (exchangeIdList.isEmpty()) {
                    emit(LoadingEvent.Success(emptyList()))
                    return@flow
                }

                when (val detailsResult = repository.getExchangesInfoList(exchangeIdList)) {
                    is Either.Left -> emit(LoadingEvent.Error(detailsResult.value))
                    is Either.Right -> emit(LoadingEvent.Success(detailsResult.value))
                }
            }
        }

    }.flowOn(Dispatchers.IO)
}

