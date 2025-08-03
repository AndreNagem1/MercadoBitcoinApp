package com.mercado.bitcoin.app

import android.app.Application
import com.mercado.bitcoin.core.di.coreModule
import com.mercado.bitcoin.exchanges_data.di.exchangesDataModule
import com.mercado.bitcoin.exchanges_presentation.di.exchangePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration

@OptIn(KoinExperimentalAPI::class)
class MercadoBitcoinApp : Application(), KoinStartup {

    @KoinExperimentalAPI
    override fun onKoinStartup() = koinConfiguration {
        androidContext(this@MercadoBitcoinApp)
        modules(
            coreModule,
            exchangesDataModule,
            exchangePresentationModule
        )
    }
}