package com.ltn.openweather.ui.fragments.presenter

import com.arellomobile.mvp.InjectViewState
import com.ltn.openweather.App
import com.ltn.openweather.Utils
import com.ltn.openweather.base.BasePresenter
import com.ltn.openweather.model.Weather
import com.ltn.openweather.room.WeatherDao
import com.ltn.openweather.ui.fragments.interactor.CityAddDialogFragmentInteractor
import com.ltn.openweather.ui.fragments.interactor.OnRequestCitiesListener
import com.ltn.openweather.ui.fragments.view.AddCityFragmentView
import javax.inject.Inject


@InjectViewState
class CityAddDialogFragmentPresenter : BasePresenter<AddCityFragmentView>(), ICityAddDialogFragmentPresenter,
    OnRequestCitiesListener {

    @Inject
    lateinit var cityAddDialogFragmentInteractor: CityAddDialogFragmentInteractor

    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var weatherDao: WeatherDao

    override fun attach() {
        App.appComponent.inject(this)
    }

    override fun loadCities(cityName: String) {
        cityAddDialogFragmentInteractor.requestCitiesFromServer(cityName, this)
    }

    override fun detach() {
    }

    override fun onSuccessful(citiesWeather: MutableList<Weather>) {
        viewState.showCitiesOnRecycler(citiesWeather)
    }

    override fun onFailure() {
        if (!utils.isNetworkAvailable()) {
            viewState.showConnectionProblem()
        }
    }

    override fun addCityWeather(weather: Weather) {
        weatherDao.insert(weather)
    }
}