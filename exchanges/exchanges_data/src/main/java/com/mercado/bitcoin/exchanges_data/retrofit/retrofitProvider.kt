package com.mercado.bitcoin.exchanges_data.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(apiKey = "9fd0687e-0615-4fa8-be2b-0b16b04fa236"))
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
