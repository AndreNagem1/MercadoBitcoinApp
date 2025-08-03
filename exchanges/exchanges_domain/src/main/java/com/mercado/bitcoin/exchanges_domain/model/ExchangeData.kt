package com.mercado.bitcoin.exchanges_domain.model

data class ExchangeData(
    val id: String,
    val name: String?,
    val volumePerDayUsd: Double
)
