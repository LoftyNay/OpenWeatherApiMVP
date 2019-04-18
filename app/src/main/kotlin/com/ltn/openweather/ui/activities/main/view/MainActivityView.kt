package com.ltn.openweather.ui.activities.main.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.ltn.openweather.base.BaseView
import com.ltn.openweather.model.Weather

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainActivityView : BaseView {
    fun showWeathersList(weathersList: MutableList<Weather>)
    fun showToast(message: String)
}