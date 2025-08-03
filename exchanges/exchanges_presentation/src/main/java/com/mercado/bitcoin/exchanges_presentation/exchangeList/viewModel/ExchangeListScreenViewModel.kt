package com.mercado.bitcoin.exchanges_presentation.exchangeList.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.mercado.bitcoin.core.network.LoadingEvent
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeRepository

class ExchangeListScreenViewModel(private val repository: ExchangeRepository) : ViewModel() {

    private val _exchangeList =
        MutableStateFlow<LoadingEvent<List<ExchangeData>>>(LoadingEvent.Loading)

    val state = _exchangeList
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), LoadingEvent.Loading)
        .onStart { getExchangeList() }

    private fun getExchangeList() {
        viewModelScope.launch {
            repository.getExchangeList().collect {
                _exchangeList.value = it
            }
        }
    }
}