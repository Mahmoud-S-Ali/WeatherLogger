package com.example.feature_weatherinformation.data.repository

import com.example.WeatherApp
import com.example.core.util.Resource
import com.example.feature_weatherinformation.data.local.WeatherInfoDao
import com.example.feature_weatherinformation.data.remote.WeatherApi
import com.example.feature_weatherinformation.domain.model.WeatherInfo
import com.example.feature_weatherinformation.domain.repository.WeatherInfoRepository
import com.example.weatherlogger.BuildConfig
import com.example.weatherlogger.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class WeatherInfoRepositoryImpl(
    private val api: WeatherApi,
    private val dao: WeatherInfoDao
): WeatherInfoRepository {

    override fun getWeatherInfo(city: String): Flow<Resource<WeatherInfo>> = flow {
        emit(Resource.Loading())

        val weatherInfo = dao.getWeatherInfo(city)?.toWeatherInfo()
        emit(Resource.Loading(weatherInfo))

        try {
            val remoteWeatherInfo = api.getWeatherInfo(city, BuildConfig.WEATHER_API_KEY)
            dao.deleteWeatherInfo(city)
            dao.insertWeatherInfo(remoteWeatherInfo.toWeatherInfoEntity())

        } catch (ioException: IOException) {
            emit(Resource.Error(
                message = WeatherApp.appContext.getString(R.string.error_server_connectoin_failed),
                data = null
            ))
        } catch (ex: Exception) {
            var msg = ex.message.toString()
            if (msg.contains("404"))
                msg = WeatherApp.appContext.getString(R.string.error_not_found_city)

            emit(Resource.Error(
                message = msg,
                data = null
            ))
        }

        val newWeatherInfo = dao.getWeatherInfo(city).toWeatherInfo()
        emit(Resource.Success(newWeatherInfo))
    }
}