package com.example.feature_weatherinformation.presentation.weatherinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.BaseRecyclerAdapter
import com.example.core.util.Utils
import com.example.feature_weatherinformation.domain.model.WeatherInfo
import com.example.weatherlogger.R
import com.example.weatherlogger.databinding.ItemWeatherInfoBinding

class WeatherInfoRecyclerAdapter(
    items: List<WeatherInfo>? = null,
    emptyView: View? = null,
    clickListener: BaseRecyclerAdapterClickListener<WeatherInfo>? = null
) : BaseRecyclerAdapter<WeatherInfo>(items, emptyView, clickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<WeatherInfo> {
        val itemBinding = ItemWeatherInfoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return WeatherInfoViewHolder(itemBinding)
    }

    private inner class WeatherInfoViewHolder(private val binding: ItemWeatherInfoBinding) :
        BaseViewHolder<WeatherInfo>(binding.root) {

        override fun bind(type: WeatherInfo) {
            super.bind(type)

            type.let {
                binding.tvTempValue.text = context.getString(R.string.degree_cel, it.temp.temp.toString())
                binding.tvDateValue.text = Utils.formatDate(it.date)
            }
        }
    }
}