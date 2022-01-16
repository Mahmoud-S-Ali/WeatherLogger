package com.example.feature_weatherinformation.domain.use_case

import com.example.core.util.Resource
import com.example.feature_weatherinformation.domain.model.WeatherInfo
import com.example.feature_weatherinformation.domain.repository.WeatherInfoRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherInfo(private val repository: WeatherInfoRepository) {

    operator fun invoke(city: String): Flow<Resource<WeatherInfo>> {
        return repository.getWeatherInfo(city)
    }
}