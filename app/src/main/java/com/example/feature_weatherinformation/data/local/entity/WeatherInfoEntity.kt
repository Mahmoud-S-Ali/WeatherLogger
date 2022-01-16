package com.example.feature_weatherinformation.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_weatherinformation.domain.model.Temp
import com.example.feature_weatherinformation.domain.model.WeatherInfo

@Entity
data class WeatherInfoEntity(
    val city: String,
    val temp: Temp,
    val date: Long,
    @PrimaryKey val id: Int? = null
) {
    fun toWeatherInfo(): WeatherInfo {
        // TODO: Return date in a formatted string
        return WeatherInfo(city, temp, date.toString())
    }
}
