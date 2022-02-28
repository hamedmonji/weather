package com.example.weather.forecastList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.ForecastDateFormatUseCase
import com.example.weather.R
import com.example.weather.data.forecast.ForecastData

class ForecastRecyclerAdapter(
    private val forecasts: List<ForecastData>,
    private val onForecastClicked: (ForecastData) -> Unit
) :
    RecyclerView.Adapter<ForecastRecyclerAdapter.ForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_forecast, parent, false)
        return ForecastViewHolder(view) { position ->
            onForecastClicked(forecasts[position])
        }
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecastData = forecasts[position]
        holder.bind(forecastData)
    }

    override fun getItemCount() = forecasts.size

    class ForecastViewHolder(
        itemView: View,
        private val onForecastClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val date: TextView = itemView.findViewById(R.id.date)
        private val weather: TextView = itemView.findViewById(R.id.weather)
        private val temp: TextView = itemView.findViewById(R.id.temp)
        private val weatherDescription: TextView = itemView.findViewById(R.id.weatherDescription)

        init {
            itemView.setOnClickListener {
                onForecastClicked(adapterPosition)
            }
        }

        fun bind(forecastData: ForecastData) {
            temp.text =
                itemView.context.getString(R.string.temperature, forecastData.temperature.day)
            weather.text = forecastData.weather.firstOrNull()?.main
            weatherDescription.text = forecastData.weather.firstOrNull()?.description
            date.text = ForecastDateFormatUseCase(forecastData).format()
        }
    }

}