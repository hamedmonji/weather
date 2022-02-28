package com.example.weather.forecastList.data

import com.example.weather.data.forecast.ForecastData

interface ForecastRepository {
    suspend fun getForecast() : List<ForecastData>
}