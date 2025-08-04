package com.mercado.bitcoin.exchanges_presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun fakeLazyPagingItems(data: List<ExchangeData>): LazyPagingItems<ExchangeData> {
    val pagingFlow = remember {
        MutableStateFlow(PagingData.from(data))
    }
    return pagingFlow.collectAsLazyPagingItems()
}