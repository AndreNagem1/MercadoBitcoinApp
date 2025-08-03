package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_domain.model.ExchangeDetails
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeId
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeRepository
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic.ExchangeDetailsScreenEvent
import kotlinx.coroutines.flow.onStart

class ExchangeDetailsScreenViewModel(
    private val repository: ExchangeRepository,
) : ViewModel() {

    var exchangeId: ExchangeId? = null

    private val _exchangeDetails =
        MutableStateFlow<LoadingEvent<ExchangeDetails>>(LoadingEvent.Loading)

    val state = _exchangeDetails
        .onStart { loadExchangeDetails() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            LoadingEvent.Loading
        )

    fun onEvent(event: ExchangeDetailsScreenEvent) {
        when (event) {
            ExchangeDetailsScreenEvent.RetryInitialCall -> {
                loadExchangeDetails()
            }
        }
    }

    private fun loadExchangeDetails() {
//        val id = exchangeId.orEmpty()
//        if (id.isBlank()) return
//
//        viewModelScope.launch {
//            repository.getExchangeDetails(id).collect {
//                _exchangeDetails.value = it
//            }
//        }
    }
}