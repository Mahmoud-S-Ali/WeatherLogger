package com.example.feature_weatherinformation.data.remote.dto

import com.example.feature_weatherinformation.domain.model.Temp

data class TempDto(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
) {
    fun toTemp(): Temp {
        return Temp(
            Math.round(feels_like).toInt(),
            humidity,
            pressure,
            Math.round(temp).toInt(),
            Math.round(temp_max).toInt(),
            Math.round(temp_min).toInt()
        )
    }
}