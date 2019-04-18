package com.ltn.openweather.ui.activities.main.presenter

interface IMainActivityPresenter {
    fun loadWeathersList()
    fun deleteWeather(id: Long)
    fun updateWeatherFromService()
    fun setTimeToRefreshWeather(hourOfDay: Int, minute: Int)
}