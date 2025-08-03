package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_domain.model.ExchangeDetails
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.onStart

class ExchangeDetailsScreenViewModel(
    private val repository: ExchangeRepository,
) : ViewModel() {

    var exchangeId: String? = null

    private val _exchangeDetails =
        MutableStateFlow<LoadingEvent<ExchangeDetails>>(LoadingEvent.Loading)

    val state = _exchangeDetails
        .onStart { getExchangeDetails(exchangeID = exchangeId.orEmpty()) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            LoadingEvent.Loading
        )

    private fun getExchangeDetails(exchangeID: String) {
        viewModelScope.launch {
            repository.getExchangeDetails(exchangeID = exchangeID).collect {
                _exchangeDetails.value = it
            }
        }
    }
}