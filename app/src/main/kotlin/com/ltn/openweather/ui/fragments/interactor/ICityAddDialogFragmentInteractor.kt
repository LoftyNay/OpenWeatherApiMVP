package com.ltn.openweather.ui.fragments.interactor

interface ICityAddDialogFragmentInteractor {
    fun requestCitiesFromServer(cityName: String, onRequestCitiesListener: OnRequestCitiesListener)
}