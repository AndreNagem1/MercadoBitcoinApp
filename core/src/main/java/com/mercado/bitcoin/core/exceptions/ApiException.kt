package com.mercado.bitcoin.core.exceptions

sealed class ApiException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class NetworkException(cause: Throwable) : ApiException("Network error occurred", cause)
    class EmptyBodyException : ApiException("Response body is null")
    class ApiErrorException(code: Int, message: String) : ApiException("API error $code: $message")
    class UnexpectedException(cause: Throwable) :
        ApiException("Unexpected error occurred", cause)
}
