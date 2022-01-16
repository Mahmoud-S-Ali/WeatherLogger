package com.example.feature_weatherinformation.di

import com.example.feature_weatherinformation.data.local.WeatherDatabase
import com.example.feature_weatherinformation.data.remote.WeatherApi
import com.example.feature_weatherinformation.data.repository.WeatherInfoRepositoryImpl
import com.example.feature_weatherinformation.domain.repository.WeatherInfoRepository
import com.example.feature_weatherinformation.domain.use_case.GetWeatherInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideGetWeatherInfoUseCase(repository: WeatherInfoRepository): GetWeatherInfo {
        return GetWeatherInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWeatherInfoRepository(
        db: WeatherDatabase,
        api: WeatherApi
    ): WeatherInfoRepository {
        return WeatherInfoRepositoryImpl(api, db.dao)
    }
}