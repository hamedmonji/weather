package com.example.weather.forecastList.data

import com.example.weather.data.forecast.ForecastModel
import retrofit2.http.GET

interface ForecastService {

    @GET("forecast/daily?q=Paris&cnt=16")
    suspend fun getParisForecast() : ForecastModel

}