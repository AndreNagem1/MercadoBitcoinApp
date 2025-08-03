package com.mercado.bitcoin.core_ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.mercado.bitcoin.core.exceptions.ApiException

@Composable
fun BaseScreen(
    screenTitle: String,
    isLoading: Boolean = false,
    isError: Boolean = false,
    error: ApiException? = null,
    retryInitialCall: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Column {
        TopBar(screenTitle = screenTitle)
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
                .fillMaxWidth()
        ) {
            content()

            if (isLoading) {
                Column(
                    modifier = Modifier
                        .testTag("LoadingScreen")
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Color.Blue)
                }
            }

            if (isError) {
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
                    Button(
                        modifier = Modifier.padding(bottom = 24.dp),
                        onClick = {
                            retryInitialCall()
                        }
                    ) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}