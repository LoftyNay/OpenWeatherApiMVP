package com.ltn.openweather.ui.fragments.view

import com.arellomobile.mvp.MvpView
import com.ltn.openweather.base.BaseView
import com.ltn.openweather.model.Weather


interface AddCityFragmentView : BaseView {
    fun showCitiesOnRecycler(citiesWeather: MutableList<Weather>)
}