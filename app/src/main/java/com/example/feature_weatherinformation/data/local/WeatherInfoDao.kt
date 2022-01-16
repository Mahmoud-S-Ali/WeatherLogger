package com.example.feature_weatherinformation.data.local

import androidx.room.*
import com.example.feature_weatherinformation.data.local.entity.WeatherInfoEntity
import com.example.feature_weatherinformation.domain.model.WeatherInfo

@Dao
interface WeatherInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherInfo(weatherInfoEntity: WeatherInfoEntity)

    @Query("DELETE FROM WeatherInfoEntity WHERE UPPER(city) = UPPER((:city))")
    suspend fun deleteWeatherInfo(city: String)

    @Query("SELECT * FROM WeatherInfoEntity WHERE UPPER(CITY) = UPPER(:city)")
    suspend fun getWeatherInfo(city: String): WeatherInfoEntity
}