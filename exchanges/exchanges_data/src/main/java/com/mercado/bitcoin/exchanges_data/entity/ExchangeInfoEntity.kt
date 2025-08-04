package com.mercado.bitcoin.exchanges_data.entity


import com.google.gson.annotations.SerializedName
import com.mercado.bitcoin.exchanges_domain.repository.ExchangeId
import kotlinx.serialization.Serializable

data class ExchangeInfoEntity(
    @SerializedName("data")
    val data: Map<ExchangeId, ExchangeInfo>,
    @SerializedName("status")
    val status: Status
)

data class ExchangeInfo(
    @SerializedName("countries")
    val countries: List<Any>,
    @SerializedName("date_launched")
    val dateLaunched: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("fiats")
    val fiats: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("maker_fee")
    val makerFee: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("notice")
    val notice: Any?,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("spot_volume_last_updated")
    val spotVolumeLastUpdated: String,
    @SerializedName("spot_volume_usd")
    val spotVolumeUsd: Double,
    @SerializedName("tags")
    val tags: Any?,
    @SerializedName("taker_fee")
    val takerFee: Double,
    @SerializedName("type")
    val type: String,
    @SerializedName("urls")
    val urls: Urls,
    @SerializedName("weekly_visits")
    val weeklyVisits: Int
)

data class Urls(
    @SerializedName("blog")
    val blog: List<Any>,
    @SerializedName("chat")
    val chat: List<String>,
    @SerializedName("fee")
    val fee: List<String>,
    @SerializedName("twitter")
    val twitter: List<String>,
    @SerializedName("website")
    val website: List<String>
)

data class Status(
    @SerializedName("credit_count")
    val creditCount: Int,
    @SerializedName("elapsed")
    val elapsed: Int,
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("error_message")
    val errorMessage: String?,
    @SerializedName("notice")
    val notice: String?,
    @SerializedName("timestamp")
    val timestamp: String
)
