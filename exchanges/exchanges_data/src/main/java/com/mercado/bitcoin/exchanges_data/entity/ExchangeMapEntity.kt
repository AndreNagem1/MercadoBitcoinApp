package com.mercado.bitcoin.exchanges_data.entity


import com.google.gson.annotations.SerializedName

data class ExchangeMapEntity(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Status
) {
    data class Data(
        @SerializedName("first_historical_data")
        val firstHistoricalData: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_active")
        val isActive: Int,
        @SerializedName("last_historical_data")
        val lastHistoricalData: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("slug")
        val slug: String,
        @SerializedName("status")
        val status: String
    )

    data class Status(
        @SerializedName("credit_count")
        val creditCount: Int,
        @SerializedName("elapsed")
        val elapsed: Int,
        @SerializedName("error_code")
        val errorCode: Int,
        @SerializedName("error_message")
        val errorMessage: String,
        @SerializedName("notice")
        val notice: String,
        @SerializedName("timestamp")
        val timestamp: String
    )
}