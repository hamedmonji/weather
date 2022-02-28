package com.example.weather.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkSetup {
    //q=Paris&mode=json&units=metric&cnt=16&=648a3aac37935e5b45e09727df728ac2
    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        val url = chain.request().url().newBuilder()
            .addQueryParameter("APPID", "648a3aac37935e5b45e09727df728ac2")
            .addQueryParameter("units","metric")
            .build()
        requestBuilder.url(url)
        chain.proceed(requestBuilder.build())
    }.build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
}