package com.ltn.openweather.base

import com.ltn.openweather.App
import com.ltn.openweather.network.ApiOpenWeather
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseInteractor {

    @Inject
    lateinit var apiOpenWeather: ApiOpenWeather

    lateinit var disposable: Disposable

    init {
        App.appComponent.inject(this)
    }
}