package com.ltn.openweather.ui.activities.view

import com.arellomobile.mvp.MvpView
import com.ltn.openweather.base.BaseView
import com.ltn.openweather.model.Weather

interface MainActivityView: BaseView {
    fun showWeathersList(weathersList: MutableList<Weather>)
    fun showProgress()
    fun hideProgress()
}