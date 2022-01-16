package com.example.feature_weatherinformation.data.remote

import com.example.feature_weatherinformation.data.remote.dto.WeatherInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getWeatherInfo(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") unit: String? = TEMP_UNIT
    ): WeatherInfoDto

    companion object {
        const val BASE_URL = "https://api.openweathermap.org"
        const val TEMP_UNIT = "metric"
    }
}