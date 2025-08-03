package com.mercado.bitcoin.exchanges_data.remote

import com.mercado.bitcoin.exchanges_data.entity.ExchangeInfoEntity
import com.mercado.bitcoin.exchanges_data.entity.ExchangeListEntityItem
import com.mercado.bitcoin.exchanges_data.entity.ExchangeMapEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeListApi {

    @GET("exchange/map")
    suspend fun getExchangeList(
        @Query("start") start: Int,
        @Query("limit") limit: Int = 10
    ): Response<ExchangeMapEntity>

    @GET("exchange/info")
    suspend fun getExchangeInfoList(
        @Query("id") idList: String,
    ): Response<ExchangeInfoEntity>

    @GET("exchanges/{exchange_id}")
    suspend fun getExchangeDetails(
        @Path("exchange_id") exchangeId: String,
    ):  Response<List<ExchangeListEntityItem>>

}