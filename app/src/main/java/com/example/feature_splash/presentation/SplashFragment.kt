package com.example.feature_splash.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.base.BaseFragment
import com.example.weatherlogger.BR
import com.example.weatherlogger.R
import com.example.weatherlogger.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataStateObservers()
    }

    override fun setupDataStateObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.observe(viewLifecycleOwner, {
                    if (it) navigateToWeatherInfoFragment()
                })
            }
        }
    }

    override fun getViewModelInstance(): SplashViewModel {
        return viewModel
    }

    override fun getViewModelBindingVariableId(): Int {
        return BR.splashViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun initViews() {
        hideStatusBar()
    }

    private fun navigateToWeatherInfoFragment() {
        val action = SplashFragmentDirections.actionSplashFragmentToWeatherInfoFragment()
        findNavController().navigate(action)
    }
}