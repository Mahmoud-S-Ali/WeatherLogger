package com.example.feature_weatherinformation.di

import com.example.feature_weatherinformation.presentation.weatherinfo.WeatherInfoRecyclerAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    fun provideWeatherInfoAdapter(): WeatherInfoRecyclerAdapter {
        return WeatherInfoRecyclerAdapter(mutableListOf())
    }
}