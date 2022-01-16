package com.example.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel: ViewModel() {

    protected var job: Job? = null

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}