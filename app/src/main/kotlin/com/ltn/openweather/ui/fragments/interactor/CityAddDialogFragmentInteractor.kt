package com.ltn.openweather.ui.fragments.interactor

import com.ltn.openweather.Constants
import com.ltn.openweather.base.BaseInteractor
import com.ltn.openweather.model.Weather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CityAddDialogFragmentInteractor : BaseInteractor(), ICityAddDialogFragmentInteractor {

    override fun requestCitiesFromServer(cityName: String, onRequestCitiesListener: OnRequestCitiesListener) {

        disposable = apiOpenWeather.findWeatherCityListByName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(1, TimeUnit.SECONDS)
            .filter { weResponse -> weResponse.list.isNotEmpty() }
            .subscribe(
                { result ->
                    val listWeather: MutableList<Weather> = ArrayList()
                    for (res in result.list) {
                        listWeather.add(
                            Weather(
                                res.id,
                                res.name,
                                res.coord.lat,
                                res.coord.lon,
                                res.main.temp.toInt(),
                                res.sys.country,
                                Constants.IMG_BASE_URL + res.weather[0].icon + ".png",
                                res.weather[0].description
                            )
                        )
                    }
                    onRequestCitiesListener.onSuccessful(listWeather)
                },
                {
                    onRequestCitiesListener.onFailure()
                }
            )
    }
}
