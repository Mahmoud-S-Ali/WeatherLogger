package com.example.feature_weatherinformation.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.feature_weatherinformation.data.local.entity.WeatherInfoEntity

@Database(entities = [WeatherInfoEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {
    abstract val dao: WeatherInfoDao
}