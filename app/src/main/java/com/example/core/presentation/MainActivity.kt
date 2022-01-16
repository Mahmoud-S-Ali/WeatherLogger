package com.example.core.presentation

import android.view.View
import com.example.base.BaseActivity
import com.example.weatherlogger.R
import com.example.weatherlogger.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun showLoading() {
        binding?.progressbar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding?.progressbar?.visibility = View.GONE
    }

    override fun showStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        actionBar?.show()
    }

    override fun hideStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }
}