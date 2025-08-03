package com.mercado.bitcoin.exchanges_presentation.di

import com.mercado.bitcoin.exchanges_presentation.exchangeList.viewModel.ExchangeListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import praonde.com.mercadobitcointeste.exchangeList.presentation.exchangeDetails.viewmodel.ExchangeDetailsScreenViewModel

val exchangePresentationModule = module {
    viewModel { ExchangeListScreenViewModel(get()) }
    viewModel { ExchangeDetailsScreenViewModel(get()) }
}