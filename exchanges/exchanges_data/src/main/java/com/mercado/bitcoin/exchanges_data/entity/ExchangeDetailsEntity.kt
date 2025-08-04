package com.mercado.bitcoin.exchanges_data.entity


import com.google.gson.annotations.SerializedName

data class ExchangeDetailsEntity(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Status
) {
    data class Data(
        @SerializedName("balance")
        val balance: Double,
        @SerializedName("currency")
        val currency: Currency,
        @SerializedName("platform")
        val platform: Platform,
        @SerializedName("wallet_address")
        val walletAddress: String
    ) {
        data class Currency(
            @SerializedName("crypto_id")
            val cryptoId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("price_usd")
            val priceUsd: Double,
            @SerializedName("symbol")
            val symbol: String
        )

        data class Platform(
            @SerializedName("crypto_id")
            val cryptoId: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("symbol")
            val symbol: String
        )
    }

    data class Status(
        @SerializedName("credit_count")
        val creditCount: Int,
        @SerializedName("elapsed")
        val elapsed: Int,
        @SerializedName("error_code")
        val errorCode: Int,
        @SerializedName("error_message")
        val errorMessage: Any,
        @SerializedName("notice")
        val notice: Any,
        @SerializedName("timestamp")
        val timestamp: String
    )
}