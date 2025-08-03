package com.mercado.bitcoin.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T, R> flowApiCall(
    apiCall: suspend () -> LoadingEvent<T>,
    transform: (T) -> R
): Flow<LoadingEvent<R>> = flow {
    emit(LoadingEvent.Loading)

    when (val result = apiCall()) {
        is LoadingEvent.Success -> emit(LoadingEvent.Success(transform(result.data)))
        is LoadingEvent.Error -> emit(LoadingEvent.Error(result.exception))
        else -> {}
    }
}.flowOn(Dispatchers.IO)
