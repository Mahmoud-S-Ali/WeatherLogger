package com.example

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import javax.inject.Inject
import android.content.Context

import io.github.inflationx.viewpump.ViewPump

@HiltAndroidApp
class WeatherApp: Application() {

    @Inject
    lateinit var calligraphyInterceptor: CalligraphyInterceptor

    companion object {
        private lateinit var _appContext: Context
        val appContext: Context
            get() = _appContext
    }

    override fun onCreate() {
        super.onCreate()
        initCalligraphy()

        _appContext = applicationContext
    }

    private fun initCalligraphy() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(calligraphyInterceptor)
                .build()
        )
    }
}