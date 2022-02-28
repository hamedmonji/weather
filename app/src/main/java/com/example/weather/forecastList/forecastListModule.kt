package com.example.weather.forecastList

import com.example.weather.data.NetworkSetup
import com.example.weather.forecastList.data.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val forecastListModule = module {
    single { NetworkSetup.retrofit }
    factory<ForecastService> {
        val retrofit: Retrofit by inject()
        retrofit.create(ForecastService::class.java)
    }
    factory { RemoteForecastDataLayer(get()) }
    factory<ForecastRepository> { ForecastRepositoryImpl(get()) }
    viewModel { ForecastListViewModel(get()) }
}