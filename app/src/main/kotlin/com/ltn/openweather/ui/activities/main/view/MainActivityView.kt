package com.ltn.openweather.ui.activities.main.view

import com.ltn.openweather.base.BaseView
import com.ltn.openweather.model.Weather

interface MainActivityView: BaseView {
    fun showWeathersList(weathersList: MutableList<Weather>)
    fun showToast(message: String)
}