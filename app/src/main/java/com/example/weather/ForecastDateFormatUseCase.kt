package com.example.weather

import com.example.weather.data.forecast.ForecastData
import java.text.DateFormat
import java.util.*

class ForecastDateFormatUseCase(private val forecastData: ForecastData) {
    fun format() : String {
        val forecastDate = Date(forecastData.time * 1000)
        return DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault()).format(forecastDate)
    }
}