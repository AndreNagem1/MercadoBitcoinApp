package com.mercado.bitcoin.exchanges_domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeData(
    val id: Int,
    val name: String?,
    val description: String,
    val logo: String,
    val spotVolumeUSD: Double,
    val dateLaunched: String,
    val makeFee: Double? = null,
    val takerFee: Double? = null,
    val website: String? = null,
)
