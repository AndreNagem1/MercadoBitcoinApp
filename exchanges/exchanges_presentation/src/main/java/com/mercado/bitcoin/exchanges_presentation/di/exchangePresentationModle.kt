package com.mercado.bitcoin.exchanges_presentation.di


import com.mercado.bitcoin.exchanges_domain.repository.ExchangeSharedRepository
import com.mercado.bitcoin.exchanges_domain.repositoryImpl.ExchangeSharedRepositoryImpl
import com.mercado.bitcoin.exchanges_presentation.exchangeList.paging.ExchangeListFactory
import com.mercado.bitcoin.exchanges_presentation.exchangeList.useCase.GetExchangeListUseCase
import com.mercado.bitcoin.exchanges_presentation.exchangeList.viewModel.ExchangeListScreenViewModel
import org.koin.dsl.module
import com.mercado.bitcoin.exchanges_presentation.exchangesDetails.viewmodel.ExchangeDetailsScreenViewModel
import org.koin.core.module.dsl.viewModel

val exchangePresentationModule = module {
    factory { GetExchangeListUseCase(get()) }
    factory { ExchangeListFactory(get()) }
    single<ExchangeSharedRepository> { ExchangeSharedRepositoryImpl() }
    viewModel { ExchangeListScreenViewModel(get(), get()) }
    viewModel { ExchangeDetailsScreenViewModel(get(), get()) }
}