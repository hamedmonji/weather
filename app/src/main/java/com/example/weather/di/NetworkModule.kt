package com.example.weather.di

import com.example.weather.data.NetworkSetup
import org.koin.dsl.module

val networkModule = module {
    single { NetworkSetup.retrofit }
}