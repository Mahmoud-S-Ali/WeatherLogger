package com.example.feature_weatherinformation.domain.repository

import com.example.core.util.Resource
import com.example.feature_weatherinformation.domain.model.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherInfoRepository {
    fun getWeatherInfo(city: String): Flow<Resource<WeatherInfo>>
}