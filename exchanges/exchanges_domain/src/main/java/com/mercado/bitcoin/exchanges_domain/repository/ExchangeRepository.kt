package praonde.com.mercadobitcointeste.exchangeList.domain.repository

import kotlinx.coroutines.flow.Flow
import praonde.com.mercadobitcointeste.common.network.LoadingEvent
import praonde.com.mercadobitcointeste.exchangeList.domain.model.ExchangeData
import praonde.com.mercadobitcointeste.exchangeList.domain.model.ExchangeDetails


interface ExchangeRepository {
    fun getExchangeList(): Flow<LoadingEvent<List<ExchangeData>>>
    fun getExchangeDetails(exchangeID : String): Flow<LoadingEvent<ExchangeDetails>>
}