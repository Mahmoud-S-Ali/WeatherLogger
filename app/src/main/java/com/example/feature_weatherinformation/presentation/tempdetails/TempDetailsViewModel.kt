package com.example.feature_weatherinformation.presentation.tempdetails

import com.example.base.BaseViewModel
import com.example.feature_weatherinformation.domain.model.Temp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TempDetailsViewModel @Inject constructor(): BaseViewModel() {
    var city: String? = null
    var tempDetails: Temp? = null
}