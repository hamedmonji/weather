package com.example.weather

import com.example.weather.data.forecast.ForecastData
import com.example.weather.data.forecast.Temp
import com.example.weather.forecastList.ForecastListViewModel
import com.example.weather.forecastList.data.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ForecastViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }


    @Test
    fun `when loading forecasts starts isFetchingForecasts should be true `() = runTest {
        val viewModel = ForecastListViewModel(TestForecastRepository(shouldFail = false))
        viewModel.loadForecasts()
        val state = viewModel.forecastState.first()
        Assert.assertTrue(
            "isFetchingForecasts should be true but is false",
            state.isFetchingForecasts
        )
    }

    @Test
    fun `when loading forecasts success state with forecastModel should be emitted `() = runTest {
        val viewModel = ForecastListViewModel(TestForecastRepository(shouldFail = false))
        viewModel.loadForecasts()
        val state = viewModel.forecastState.drop(1).first()
        Assert.assertFalse(
            "isFetchingForecasts should be false but is true",
            state.isFetchingForecasts
        )
        Assert.assertTrue("forecast model should not be empty", state.forecasts.isNotEmpty())
    }

    @Test
    fun `when loading forecasts fails message should be emitted `() = runTest {
        val viewModel = ForecastListViewModel(TestForecastRepository(shouldFail = true))
        viewModel.loadForecasts()
        val state = viewModel.forecastState.drop(1).first()
        Assert.assertFalse(
            "isFetchingForecasts should be false but is true",
            state.isFetchingForecasts
        )
        Assert.assertTrue(
            "forecastModels should be empty",
            state.forecasts.isEmpty()
        )
        Assert.assertNotNull("message should not be null", state.message)
    }

    class TestForecastRepository(private val shouldFail: Boolean = false) : ForecastRepository {
        override suspend fun getForecast(): List<ForecastData> {
            if (shouldFail) {
                throw IllegalStateException("error")
            } else {
                return listOf(
                    ForecastData(
                        222222,
                        22223,
                        21214,
                        123,
                        241,
                        12,
                        Temp(21F, 12F, 28F, 15F,18F,12F),
                        emptyList()
                    )
                )
            }
        }
    }
}