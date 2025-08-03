package com.mercado.bitcoin.core.di

import com.mercado.bitcoin.core.retrofit.provideRetrofit
import org.koin.dsl.module

val coreModule = module {
    single { provideRetrofit() }
}