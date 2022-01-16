package com.example.feature_weatherinformation.presentation.weatherinfo

import androidx.lifecycle.viewModelScope
import com.example.WeatherApp
import com.example.base.BaseViewModel
import com.example.core.util.Resource
import com.example.feature_weatherinformation.domain.model.WeatherInfo
import com.example.feature_weatherinformation.domain.use_case.GetWeatherInfo
import com.example.weatherlogger.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherInfoViewModel @Inject constructor(
    private val getWeatherInfo: GetWeatherInfo
): BaseViewModel() {

    private var _city: String? = null
    val city: String?
        get() = _city

    private var _weatherInfo: WeatherInfo? = null
    val weatherInfo: WeatherInfo?
        get() = _weatherInfo

    private val _state = MutableStateFlow(WeatherInfoState())
    val state: StateFlow<WeatherInfoState>
        get() = _state

    // 5 minutes
    private val periodicDelayMillis: Long = 5 * 60 * 1000

    private var periodicJob: Job? = null


    fun onSaveBtnClicked(city: String) {
        job?.cancel()

        if (!isValidCity(city)) {
            _state.value = WeatherInfoState(
                isLoading = false,
                errorMsg = WeatherApp.appContext.getString(R.string.error_empty_city)
            )
            return
        }

        job = viewModelScope.launch {
            getWeatherInfo(city).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _city = city
                        _weatherInfo = result.data
                        _state.value = WeatherInfoState(
                            weatherInfo = result.data,
                            isLoading = false
                        )

                        startPeriodicTask()
                    }
                    is Resource.Error -> {
                        _state.value = WeatherInfoState(
                            weatherInfo = result.data,
                            isLoading = false,
                            errorMsg = result.message
                        )

                        startPeriodicTask()
                    }
                    is Resource.Loading -> {
                        _state.value = WeatherInfoState(
                            weatherInfo = result.data,
                            isLoading = true
                        )
                    }
                }
            }.catch {
                    cause -> Timber.e(cause.message.toString())
            }.launchIn(this)
        }
    }

    // Starts a periodic task for refreshing the data
    // There are other ways for implementing this function
    // We can use WorkManager or Handler
    private fun startPeriodicTask() {
        if (!isValidCity(_city ?: ""))
            return

        periodicJob?.cancel()
        periodicJob = viewModelScope.launch {
            delay(periodicDelayMillis)
            onSaveBtnClicked(city ?: "")
        }
    }

    fun isValidCity(city: String): Boolean {
        return city.isNotEmpty()
    }

    override fun onCleared() {
        super.onCleared()
        periodicJob?.cancel()
    }
}