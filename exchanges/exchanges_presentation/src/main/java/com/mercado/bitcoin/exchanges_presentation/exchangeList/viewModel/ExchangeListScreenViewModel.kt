package com.mercado.bitcoin.exchanges_presentation.exchangeList.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mercado.bitcoin.exchanges_domain.model.ExchangeData
import com.mercado.bitcoin.exchanges_presentation.exchangeList.paging.ExchangeListFactory
import kotlinx.coroutines.flow.Flow

class ExchangeListScreenViewModel(
    pagingSourceFactory: ExchangeListFactory
) : ViewModel() {

    val pagingData: Flow<PagingData<ExchangeData>> =
        pagingSourceFactory().cachedIn(viewModelScope)

}