package com.ltn.openweather.service

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.ltn.openweather.App
import com.ltn.openweather.Constants
import com.ltn.openweather.model.Weather
import com.ltn.openweather.network.ApiOpenWeather
import com.ltn.openweather.room.WeatherDao
import com.ltn.openweather.ui.activities.main.presenter.MainActivityPresenter
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherService : IntentService(Constants.SERVICE_NAME) {

    @Inject
    lateinit var apiOpenWeather: ApiOpenWeather

    @Inject
    lateinit var weatherDao: WeatherDao

    private lateinit var disposable: Disposable

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onHandleIntent(intent: Intent?) {
        val list = weatherDao.getAll()
        for (weather in list) {
            updateByID(weather.id)
        }
    }

    private fun updateByID(id: Long?) {
        disposable = apiOpenWeather.findWeatherCityById(id!!).debounce(5, TimeUnit.SECONDS)
            .subscribe { result ->
                weatherDao.update(
                    Weather(
                        result.id, result.name, result.coord.lat, result.coord.lon,
                        result.main.temp.toInt(), result.sys.country,
                        Constants.IMG_BASE_URL + result.weather[0].icon + Constants.PNG_TYPE,
                        result.weather[0].description
                    )
                )
                Log.d("GLL", result.name)
            }
    }

    override fun onCreate() {
        App.appComponent.inject(this)
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        sendBroadcast(Intent(MainActivityPresenter.ACTION_UPDATE_WEATHER))
        disposable.dispose()
    }
}