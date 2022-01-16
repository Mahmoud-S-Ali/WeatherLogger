package com.example.feature_splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): BaseViewModel() {

    private val timeDelayInMillis: Long = 2000

    private var _state = MutableLiveData(false)
    val state: LiveData<Boolean>
        get() = _state

    init {
        startTimer()
    }

    fun startTimer() {
        job?.cancel()
        job = viewModelScope.launch {
            delay(timeDelayInMillis)
            _state.value = true
        }
    }
}