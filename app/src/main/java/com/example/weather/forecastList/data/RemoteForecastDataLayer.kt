package com.example.weather.forecastList.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteForecastDataLayer(
    private val forecastService: ForecastService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ForecastDataLayer {
    override suspend fun getForecast() = withContext(dispatcher) {
        forecastService.getParisForecast().forecasts
    }
}