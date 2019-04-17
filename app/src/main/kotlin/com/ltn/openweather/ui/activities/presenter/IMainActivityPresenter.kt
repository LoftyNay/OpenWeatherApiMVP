package com.ltn.openweather.ui.activities.presenter

interface IMainActivityPresenter {
    fun loadWeathersList()
    fun deleteWeather(id: Long)
    fun updateWeatherFromService()
    fun setTimeToRefreshWeather(hourOfDay: Int, minute: Int)
}