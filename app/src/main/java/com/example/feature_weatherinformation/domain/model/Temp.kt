package com.example.feature_weatherinformation.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temp(
    val feels_like: Int,
    val humidity: Int,
    val pressure: Int,
    val temp: Int,
    val temp_max: Int,
    val temp_min: Int
) : Parcelable