package com.mercado.bitcoin.exchanges_domain.model

import com.mercado.bitcoin.exchanges_domain.repository.ExchangeId

data class ExchangeData(
    val id: ExchangeId,
    val name: String?,
    val logo: String,
    val spotVolumeUSD: Double,
    val dateLaunched: String
)
