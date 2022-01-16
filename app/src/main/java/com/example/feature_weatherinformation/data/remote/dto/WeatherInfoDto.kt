package com.example.feature_weatherinformation.data.remote.dto

import com.example.feature_weatherinformation.data.local.entity.WeatherInfoEntity

data class WeatherInfoDto(
    val base: String,
    val clouds: CloudsDto,
    val cod: Int,
    val coord: CoordDto,
    val dt: Int,
    val id: Int,
    val main: TempDto,
    val name: String,
    val sys: SysDto,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDto>,
    val wind: WindDto
) {
    fun toWeatherInfoEntity(): WeatherInfoEntity {
        return WeatherInfoEntity(name, main.toTemp(), System.currentTimeMillis())
    }
}