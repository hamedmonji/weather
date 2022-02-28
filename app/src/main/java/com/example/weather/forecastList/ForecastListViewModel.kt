package com.example.weather.forecastList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.forecast.ForecastData
import com.example.weather.forecastList.data.ForecastRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class ForecastListViewModel(private val forecastRepository: ForecastRepository) : ViewModel() {

    data class ForecastUiState(
        val forecasts: List<ForecastData> = emptyList(),
        val isFetchingForecasts: Boolean = false,
        val messages: Queue<String> = ArrayDeque()
    )

    private val _forecastState = MutableStateFlow(ForecastUiState())
    val forecastState: StateFlow<ForecastUiState> = _forecastState.asStateFlow()

    init {
        loadForecasts()
    }


    fun loadForecasts() {
        _forecastState.update { ForecastUiState(isFetchingForecasts = true) }
        viewModelScope.launch {
            try {
                val forecasts = forecastRepository.getForecast()
                _forecastState.update {
                    ForecastUiState(forecasts, isFetchingForecasts = false)
                }
            } catch (e: Throwable) {
                _forecastState.update {
                    it.messages.offer(e.message)
                    ForecastUiState(messages = it.messages)
                }
            }
        }
    }

}