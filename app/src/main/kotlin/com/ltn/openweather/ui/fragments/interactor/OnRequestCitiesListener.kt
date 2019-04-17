package com.ltn.openweather.ui.fragments.interactor

import com.ltn.openweather.model.Weather

interface OnRequestCitiesListener {
    fun onSuccessful(citiesWeather: MutableList<Weather>)
    fun onFailure()
}