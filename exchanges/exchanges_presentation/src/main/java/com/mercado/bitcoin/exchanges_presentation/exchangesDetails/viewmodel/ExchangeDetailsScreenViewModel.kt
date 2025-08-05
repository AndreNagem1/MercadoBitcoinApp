package com.mercado.bitcoin.exchanges_presentation.exchangesDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_domain.model.CurrencyInfo
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeRepository
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeSharedRepository
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic.ExchangeDetailsScreenEvent
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.uiLogic.ExchangeDetailsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExchangeDetailsScreenViewModel(
    private val repository: ExchangeRepository,
    sharedRepository: ExchangeSharedRepository
) : ViewModel() {

    private val _exchangeData = sharedRepository.getCurrentExchangeData()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    private val _exchangeDetails =
        MutableStateFlow<LoadingEvent<List<CurrencyInfo>>>(LoadingEvent.Loading)


    val state = _exchangeDetails
        .combine(_exchangeData) { exchangeDetails,
                                  exchangeData ->

            ExchangeDetailsScreenState(
                exchangeData = exchangeData,
                listCurrencyLoadingEvent = exchangeDetails
            )
        }.onStart { observeExchangeDataAndLoadDetails() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ExchangeDetailsScreenState()
        )

    fun onEvent(event: ExchangeDetailsScreenEvent) {
        when (event) {
            ExchangeDetailsScreenEvent.RetryInitialCall -> {
                _exchangeData.value?.id?.let {
                    loadExchangeDetails(it.toString())
                }
            }
        }
    }

    private fun observeExchangeDataAndLoadDetails() {
        viewModelScope.launch {
            _exchangeData.collect { exchangeData ->
                if (exchangeData != null) {
                    loadExchangeDetails(exchangeData.id.toString())
                }
            }
        }
    }

    private fun loadExchangeDetails(exchangeId: String) {
        if (exchangeId.isBlank()) return

        viewModelScope.launch {
            when (val result = repository.getExchangeDetails(exchangeId)) {
                is Either.Left -> _exchangeDetails.value = LoadingEvent.Error(result.value)
                is Either.Right -> _exchangeDetails.value = LoadingEvent.Success(result.value)
            }
        }
    }
}