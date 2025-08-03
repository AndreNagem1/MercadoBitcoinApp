package com.mercado.bitcoin.core.retrofit

import com.mercado.bitcoin.core.BuildConfig
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
        .baseUrl("https://pro-api.coinmarketcap.com/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("X-CMC_PRO_API_KEY", apiKey)
            .build()

        return chain.proceed(newRequest)
    }
}
