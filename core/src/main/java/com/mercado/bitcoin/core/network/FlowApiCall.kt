package com.mercado.bitcoin.core.network

import arrow.core.Either
import com.mercado.bitcoin.core.exceptions.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T, R> flowApiCall(
    apiCall: suspend () -> Either<ApiException, T>,
    transform: (T) -> R
): Flow<LoadingEvent<R>> = flow {
    emit(LoadingEvent.Loading)

    when (val result = apiCall()) {
        is Either.Right -> emit(LoadingEvent.Success(transform(result.value)))
        is Either.Left -> emit(LoadingEvent.Error(result.value))
    }
}.flowOn(Dispatchers.IO)
