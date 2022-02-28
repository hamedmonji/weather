package com.example.weather.data.forecast

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastModel(
    @SerializedName("list") val forecasts: List<ForecastData>
) : Parcelable

@Parcelize
data class ForecastData(
    @SerializedName("dt") val time: Long,
    val sunrise: Long,
    val sunset: Long,
    val pressure: Int,
    val humidity: Int,
    val clouds: Int,
    @SerializedName("temp") val temperature: Temp,
    val weather: List<Weather>
) : Parcelable

@Parcelize
data class Weather(val main: String, val description: String, val icon: String) : Parcelable

@Parcelize
data class Temp(
    val day: Float,
    val min: Float,
    val max: Float,
    val night: Float,
    @SerializedName("eve")
    val evening: Float,
    @SerializedName("morn")
    val morning: Float
) : Parcelable

@Parcelize
data class City(val name: String) : Parcelable