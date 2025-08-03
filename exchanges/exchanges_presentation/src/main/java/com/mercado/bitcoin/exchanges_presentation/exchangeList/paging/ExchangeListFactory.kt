package com.mercado.bitcoin.exchanges_presentation.exchangeList.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mercado.bitcoin.exchanges_presentation.exchangeList.useCase.GetExchangeListUseCase

class ExchangeListFactory(
    private val useCase: GetExchangeListUseCase
) {
    operator fun invoke() = Pager(PagingConfig(pageSize = 10)) {
        PagingSource(useCase = useCase)
    }.flow
}
