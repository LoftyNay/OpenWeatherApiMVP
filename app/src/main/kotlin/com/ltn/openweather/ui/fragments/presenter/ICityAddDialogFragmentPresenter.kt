package com.ltn.openweather.ui.fragments.presenter

import com.ltn.openweather.model.Weather

interface ICityAddDialogFragmentPresenter {
    fun loadCities(cityName: String)
    fun addCityWeather(weather: Weather)
}