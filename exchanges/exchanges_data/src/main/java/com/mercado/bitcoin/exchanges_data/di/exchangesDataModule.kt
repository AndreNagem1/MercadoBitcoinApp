package com.mercado.bitcoin.exchanges_data.di

import com.mercado.bitcoin.exchanges_data.remote.ExchangeDataStore
import com.mercado.bitcoin.exchanges_data.remote.ExchangeListApi
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeRepository
import org.koin.dsl.module
import com.mercado.bitcoin.exchanges_data.repository.ExchangeRepositoryImpl
import com.mercado.bitcoin.core.retrofit.provideRetrofit
import retrofit2.Retrofit

val exchangesDataModule = module {
    single { get<Retrofit>().create(ExchangeListApi::class.java) }
    single { ExchangeDataStore(get()) }
    single<ExchangeRepository> { ExchangeRepositoryImpl(get()) }
}