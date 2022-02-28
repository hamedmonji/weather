package com.example.weather.forecastDetail

import com.example.weather.data.forecast.ForecastData

class ForecastWeatherConditionUseCase(private val forecastData: ForecastData) {
    fun getWeatherCondition(): WeatherCondition {
        val temp = forecastData.temperature.day
        return when {
            temp > 25 -> {
                WeatherCondition.HOT
            }
            temp < 10 -> {
                WeatherCondition.COLD
            }
            else -> {
                WeatherCondition.NORMAL
            }
        }
    }

    enum class WeatherCondition {
        HOT,
        COLD,
        NORMAL
    }

}