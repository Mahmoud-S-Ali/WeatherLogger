package com.example.feature_weatherinformation.presentation.weatherinfo

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.base.BaseFragment
import com.example.base.BaseRecyclerAdapter
import com.example.feature_weatherinformation.domain.model.WeatherInfo
import com.example.weatherlogger.R
import com.example.weatherlogger.BR
import com.example.weatherlogger.databinding.FragmentWeatherInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WeatherInfoFragment : BaseFragment<FragmentWeatherInfoBinding, WeatherInfoViewModel>(),
    BaseRecyclerAdapter.BaseRecyclerAdapterClickListener<WeatherInfo> {

    private val viewModel: WeatherInfoViewModel by viewModels()
    @Inject
    lateinit var weatherInfoRecyclerAdapter: WeatherInfoRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataStateObservers()
    }

    override fun setupDataStateObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { weatherInfoState ->
                    if (weatherInfoState.isLoading) showLoading() else hideLoading()

                    if (weatherInfoState.errorMsg != null)
                        showToastMsg(weatherInfoState.errorMsg)

                    weatherInfoState.weatherInfo?.let {
                        hideKeyboard()
                        viewDataBinding?.tieCity?.clearFocus()
                        weatherInfoRecyclerAdapter.clearItems()
                        weatherInfoRecyclerAdapter.addItems(mutableListOf(it))
                    }
                }
            }
        }
    }

    override fun getViewModelInstance(): WeatherInfoViewModel {
        return viewModel
    }

    override fun getViewModelBindingVariableId(): Int {
        return BR.weatherInfoViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_weather_info
    }

    override fun initViews() {
        showStatusBar()
        setupWeatherInfoRV()
        addOnFetchListener()
    }

    private fun setupWeatherInfoRV() {
        viewDataBinding?.let {
            weatherInfoRecyclerAdapter.setEmptyView(it.tvEmptyView)
            weatherInfoRecyclerAdapter.setClickListener(this)
            it.rvWeatherInfo.adapter = weatherInfoRecyclerAdapter
            it.rvWeatherInfo.setHasFixedSize(true)
        }
    }

    private fun addOnFetchListener() {
        viewDataBinding?.btnFetch?.setOnClickListener {
            weatherInfoRecyclerAdapter.clearItems()
            viewModel.onSaveBtnClicked(viewDataBinding?.tieCity?.text.toString())
        }
    }

    override fun onViewClicked(item: WeatherInfo, position: Int, actionId: Int?) {
        navigateToTempDetailsFragment()
    }

    private fun navigateToTempDetailsFragment() {
        val action = WeatherInfoFragmentDirections.actionWeatherInfoFragmentToTempDetailsFragment(
            viewModel.city, viewModel.weatherInfo?.temp)
        findNavController().navigate(action)
    }
}