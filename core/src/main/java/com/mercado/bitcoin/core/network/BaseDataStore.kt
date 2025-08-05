package com.mercado.bitcoin.core.network

import com.mercado.bitcoin.core.exceptions.ApiException
import retrofit2.Response
import java.io.IOException
import arrow.core.Either
import arrow.core.left
import arrow.core.right

open class BaseDataStore() {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Either<ApiException, T> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                response.body()?.right()
                    ?: ApiException.EmptyBodyException().left()
            } else {
                ApiException.ApiErrorException(response.code(), response.message()).left()
            }
        } catch (e: IOException) {
            ApiException.NetworkException(e).left()
        } catch (e: Exception) {
            ApiException.UnexpectedException(e).left()
        }
    }


}