package com.mercado.bitcoin.exchanges_presentation.exchangeList.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag

@Composable
fun ExchangeListLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("LoadingScreen"),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.Blue)
    }
}