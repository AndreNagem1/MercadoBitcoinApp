package com.mercado.bitcoin.exchanges_domain.repositoryImpl

import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeSharedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class ExchangeSharedRepositoryImpl : ExchangeSharedRepository {

    private val _exchangeData = MutableSharedFlow<ExchangeData>(replay = 1)

    override fun setCurrentExchangeData(
        coroutineScope: CoroutineScope,
        exchangeData: ExchangeData
    ) {
        coroutineScope.launch {
            _exchangeData.emit(exchangeData)
        }
    }

    override fun getCurrentExchangeData(): SharedFlow<ExchangeData> {
        return _exchangeData
    }
}