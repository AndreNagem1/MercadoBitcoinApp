package com.mercado.bitcoin.core.network

import com.mercado.bitcoin.core.exceptions.ApiException
import retrofit2.Response
import java.io.IOException

open class BaseDataStore() {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): LoadingEvent<T> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                response.body()?.let {
                    LoadingEvent.Success(it)
                } ?: LoadingEvent.Error(ApiException.EmptyBodyException())
            } else {
                LoadingEvent.Error(ApiException.ApiErrorException(response.code(), response.message()))
            }
        } catch (e: IOException) {
            LoadingEvent.Error(ApiException.NetworkException(e))
        } catch (e: Exception) {
            LoadingEvent.Error(ApiException.UnexpectedException(e))
        }
    }

}