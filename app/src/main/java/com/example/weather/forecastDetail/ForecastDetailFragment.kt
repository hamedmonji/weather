package com.example.weather.forecastDetail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.weather.ForecastDateFormatUseCase
import com.example.weather.R

class ForecastDetailFragment : Fragment(R.layout.fragment_forecast_detail) {
    private val args  : ForecastDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val date = view.findViewById<TextView>(R.id.date)
        val weather = view.findViewById<TextView>(R.id.weather)
        val morningTemperature = view.findViewById<TextView>(R.id.morning)
        val dayTemperature = view.findViewById<TextView>(R.id.day)
        val eveningTemperature = view.findViewById<TextView>(R.id.evening)
        val nightTemperature = view.findViewById<TextView>(R.id.night)
        val minTemperature = view.findViewById<TextView>(R.id.min)
        val maxTemperature = view.findViewById<TextView>(R.id.max)
        val pressure = view.findViewById<TextView>(R.id.pressure)
        val humidity = view.findViewById<TextView>(R.id.humidity)
        val clouds = view.findViewById<TextView>(R.id.clouds)
        val weatherCondition = view.findViewById<TextView>(R.id.weatherCondition)

        val forecastData = args.forecastData

        weatherCondition.text = when (ForecastWeatherConditionUseCase(forecastData).getWeatherCondition()) {
            ForecastWeatherConditionUseCase.WeatherCondition.HOT -> getString(R.string.weather_condition_hot)
            ForecastWeatherConditionUseCase.WeatherCondition.COLD -> getString(R.string.weather_condition_cold)
            ForecastWeatherConditionUseCase.WeatherCondition.NORMAL -> getString(R.string.weather_condition_normal)
        }


        date.text = ForecastDateFormatUseCase(forecastData).format()

        weather.text = forecastData.weather.firstOrNull()?.description
        with(forecastData.temperature) {
            morningTemperature.text = getString(R.string.morning,morning)
            dayTemperature.text = getString(R.string.day,day)
            eveningTemperature.text = getString(R.string.evening,evening)
            nightTemperature.text = getString(R.string.night,night)
            minTemperature.text = getString(R.string.min,min)
            maxTemperature.text = getString(R.string.max,max)
        }

        pressure.text = getString(R.string.pressure,forecastData.pressure)
        humidity.text = getString(R.string.humidity,forecastData.humidity)
        clouds.text = getString(R.string.clouds,forecastData.clouds)

    }

}