package com.example.weather.forecastList.data

import com.example.weather.data.forecast.ForecastData

class ForecastRepositoryImpl(
    private val remoteForecastDataLayer: RemoteForecastDataLayer
) :
    ForecastRepository {

    override suspend fun getForecast(): List<ForecastData>{
        try {
            return remoteForecastDataLayer.getForecast()
        }catch (t : Throwable) {
            throw Throwable("this error would come from backend")
        }
    }
}