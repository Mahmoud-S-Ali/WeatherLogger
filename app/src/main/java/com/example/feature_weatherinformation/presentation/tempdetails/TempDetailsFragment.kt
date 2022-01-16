package com.example.feature_weatherinformation.presentation.tempdetails

import androidx.fragment.app.viewModels
import com.example.base.BaseFragment
import com.example.weatherlogger.BR
import com.example.weatherlogger.R
import com.example.weatherlogger.databinding.FragmentTempDetailsBinding

class TempDetailsFragment: BaseFragment<FragmentTempDetailsBinding, TempDetailsViewModel>() {

    private val viewModel: TempDetailsViewModel by viewModels()

    override fun getViewModelInstance(): TempDetailsViewModel {
        return viewModel
    }

    override fun getViewModelBindingVariableId(): Int {
        return BR.tempDetailsViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_temp_details
    }

    override fun initViews() {
        arguments?.let {
            viewModel.city = TempDetailsFragmentArgs.fromBundle(it).city
            viewModel.tempDetails = TempDetailsFragmentArgs.fromBundle(it).tempDetails
        }

        viewDataBinding?.tvCityName?.text = viewModel.city ?: ""
        viewDataBinding?.tvTempValue?.text = getString(R.string.degree_cel, viewModel.tempDetails?.temp.toString())
        viewDataBinding?.tvFeelsLikeValue?.text = getString(R.string.degree_cel, viewModel.tempDetails?.feels_like.toString())
        viewDataBinding?.tvMinTempValue?.text = getString(R.string.degree_cel, viewModel.tempDetails?.temp_min.toString())
        viewDataBinding?.tvMaxTempValue?.text = getString(R.string.degree_cel, viewModel.tempDetails?.temp_max.toString())
        viewDataBinding?.tvHumidityValue?.text = viewModel.tempDetails?.humidity.toString()
        viewDataBinding?.tvPressureValue?.text = viewModel.tempDetails?.pressure.toString()
    }
}