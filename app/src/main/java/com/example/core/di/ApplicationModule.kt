package com.example.core.di

import android.app.Application
import androidx.room.Room
import com.example.feature_weatherinformation.data.local.Converters
import com.example.feature_weatherinformation.data.local.WeatherDatabase
import com.example.feature_weatherinformation.data.remote.WeatherApi
import com.example.feature_weatherinformation.data.util.GsonParser
import com.example.weatherlogger.BuildConfig
import com.example.weatherlogger.R
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun provideCalligraphyInterceptor(calligraphyConfig: CalligraphyConfig): CalligraphyInterceptor {
        return CalligraphyInterceptor(calligraphyConfig)
    }

    @Provides
    @Singleton
    fun provideCalligraphyConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/roboto_regular.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherDatabase(app: Application): WeatherDatabase {
        return Room.databaseBuilder(
            app, WeatherDatabase::class.java, BuildConfig.DB_NAME)
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(client: OkHttpClient): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = if (BuildConfig.DEBUG) {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()
}