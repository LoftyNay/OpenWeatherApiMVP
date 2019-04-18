package com.ltn.openweather.ui.activities.main.presenter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.arellomobile.mvp.InjectViewState
import com.ltn.openweather.App
import com.ltn.openweather.R
import com.ltn.openweather.Utils
import com.ltn.openweather.base.BasePresenter
import com.ltn.openweather.room.WeatherDao
import com.ltn.openweather.service.WeatherReceiver
import com.ltn.openweather.service.WeatherService
import com.ltn.openweather.ui.activities.main.view.MainActivityView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*
import javax.inject.Inject


@InjectViewState
class MainActivityPresenter : BasePresenter<MainActivityView>(), IMainActivityPresenter {

    @Inject
    lateinit var weatherDao: WeatherDao

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var alarmManager: AlarmManager

    @Inject
    lateinit var eventBus: EventBus

    private lateinit var pendingIntent: PendingIntent

    private var weatherReceiver = WeatherReceiver()
    private var intentFilter = IntentFilter()

    companion object {
        const val ACTION_UPDATE_WEATHER = "com.ltn.openweather.UPDATE_WEATHER"
    }

    override fun attach() {
        App.appComponent.inject(this)

        intentFilter.addAction(ACTION_UPDATE_WEATHER)
        context.registerReceiver(weatherReceiver, intentFilter)
        eventBus.register(this)

        val intent = Intent(context, WeatherService::class.java)
        pendingIntent = PendingIntent.getService(context, 0, intent, 0)
    }

    override fun setTimeToRefreshWeather(hourOfDay: Int, minute: Int) {
        val calendar: Calendar = calendar(hourOfDay, minute)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        viewState.showToast(context.getString(R.string.update_in_time, hourOfDay.toString(), minute.toString()))
    }

    private fun calendar(hourOfDay: Int, minute: Int): Calendar {
        return Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }
    }

    @Subscribe
    fun onEvent(message: String) {
        viewState.showWeathersList(weatherDao.getAll())
        viewState.showToast(message)
        viewState.hideProgress()
    }

    override fun detach() {
        context.unregisterReceiver(weatherReceiver)
        eventBus.unregister(this)
    }

    override fun loadWeathersList() {
        viewState.showWeathersList(weatherDao.getAll())
    }

    override fun deleteWeather(id: Long) {
        weatherDao.deleteWeatherCityById(id)
    }

    override fun updateWeatherFromService() {
        if (utils.isNetworkAvailable()) {
            viewState.showProgress()
            val intent = Intent(context, WeatherService::class.java)
            val size = weatherDao.getAll().size
            if (size != 0)
                context.startService(intent)
            else viewState.hideProgress()
        } else {
            viewState.showConnectionProblem()
            viewState.hideProgress()
        }
    }
}
