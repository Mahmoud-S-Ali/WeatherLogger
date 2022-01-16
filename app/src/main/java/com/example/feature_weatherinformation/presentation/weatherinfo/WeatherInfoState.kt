package com.example.feature_weatherinformation.presentation.weatherinfo

import com.example.feature_weatherinformation.domain.model.WeatherInfo

data class WeatherInfoState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
