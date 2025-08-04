package com.mercado.bitcoin.exchanges_presentation.exchangeList.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.mercado.bitcoin.core.exceptions.ApiException

@Composable
fun ExchangeListErrorState(error: ApiException) {
    Column(
        modifier = Modifier
            .testTag("ErrorScreen")
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val errorMessage = when (error) {
            is ApiException.NetworkException -> "Check your internet connection."
            is ApiException.EmptyBodyException -> "No data received from server."
            is ApiException.ApiErrorException -> "API Error: ${error.code}"
            is ApiException.UnexpectedException -> "Unexpected error occurred."
            else -> "Something went wrong :/"
        }

        Text(text = errorMessage)
    }
}