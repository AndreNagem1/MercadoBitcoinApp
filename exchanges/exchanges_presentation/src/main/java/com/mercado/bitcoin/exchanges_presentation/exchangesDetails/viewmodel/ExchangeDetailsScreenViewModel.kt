package praonde.com.mercadobitcointeste.exchangeList.presentation.exchangeDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import praonde.com.mercadobitcointeste.common.network.LoadingEvent
import praonde.com.mercadobitcointeste.exchangeList.domain.model.ExchangeDetails
import praonde.com.mercadobitcointeste.exchangeList.domain.repository.ExchangeRepository

class ExchangeDetailsScreenViewModel(private val repository: ExchangeRepository) : ViewModel() {

    private val _exchangeDetails =
        MutableStateFlow<LoadingEvent<ExchangeDetails>>(LoadingEvent.Loading)

    val state = _exchangeDetails.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        LoadingEvent.Loading
    )

    fun getExchangeDetails(exchangeID : String) {
        viewModelScope.launch {
            repository.getExchangeDetails(exchangeID = exchangeID).collect {
                _exchangeDetails.value = it
            }
        }
    }
}