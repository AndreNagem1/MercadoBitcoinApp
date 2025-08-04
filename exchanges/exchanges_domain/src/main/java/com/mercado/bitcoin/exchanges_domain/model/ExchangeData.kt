package com.mercado.bitcoin.exchanges_domain.model

import java.time.LocalDate

data class ExchangeData(
    val id: Int,
    val name: String?,
    val description: String,
    val logo: String,
    val spotVolumeUSD: Double,
    val dateLaunched: LocalDate?,
    val makeFee: Double? = null,
    val takerFee: Double? = null,
    val website: String? = null,
)
