package com.mercado.bitcoin.core.network

import retrofit2.Response

open class BaseDataStore() {

    suspend fun <T> safeApiCall(
        apiCall:  suspend () -> Response<T>
    ): LoadingEvent<T> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                response.body()?.let {
                    LoadingEvent.Success(it)
                } ?: LoadingEvent.Error(Exception("Response body is null"))
            } else {
                LoadingEvent.Error(
                    Exception("API error: ${response.code()} ${response.message()}")
                )
            }
        } catch (e: Exception) {
            LoadingEvent.Error(Exception("Exception during API call", e))
        }
    }
}