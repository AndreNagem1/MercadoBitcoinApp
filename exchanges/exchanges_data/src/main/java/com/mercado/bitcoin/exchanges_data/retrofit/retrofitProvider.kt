package com.mercado.bitcoin.exchanges_data.retrofit

import com.mercado.bitcoin.exchanges_data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(apiKey = BuildConfig.COIN_API_KEY))
        .build()

    return Retrofit.Builder()
        .baseUrl("https://rest.coinapi.io/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("X-CoinAPI-Key", apiKey)
            .build()

        return chain.proceed(newRequest)
    }
}
