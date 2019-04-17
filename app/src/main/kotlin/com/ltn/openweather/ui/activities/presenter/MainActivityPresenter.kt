package com.ltn.openweather.ui.activities.presenter

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.ltn.openweather.App
import com.ltn.openweather.Utils
import com.ltn.openweather.base.BasePresenter
import com.ltn.openweather.room.WeatherDao
import com.ltn.openweather.service.WeatherReceiver
import com.ltn.openweather.service.WeatherService
import com.ltn.openweather.ui.activities.view.MainActivityView
import javax.inject.Inject
import android.widget.Toast
import com.ltn.openweather.ui.activities.MainActivity
import android.content.Context.ALARM_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import java.util.*


@InjectViewState
class MainActivityPresenter : BasePresenter<MainActivityView>(), IMainActivityPresenter,
    WeatherReceiver.OnReceiveListener {

    @Inject
    lateinit var weatherDao: WeatherDao

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var alarmManager: AlarmManager

    private lateinit var pendingIntent: PendingIntent

    var weatherReceiver = WeatherReceiver(this)
    var intentFilter = IntentFilter()

    companion object {
        val ACTION_UPDATE_WEATHER = "com.ltn.openweather.UPDATE_OK"
    }

    override fun attach() {
        App.appComponent.inject(this)
        intentFilter.addAction(ACTION_UPDATE_WEATHER)
        context.registerReceiver(weatherReceiver, intentFilter)

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
    }

    private fun calendar(hourOfDay: Int, minute: Int): Calendar {
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }
        return calendar
    }

    override fun detach() {
        context.unregisterReceiver(weatherReceiver)
    }

    override fun onReceive() {
        viewState.showWeathersList(weatherDao.getAll())
        viewState.hideProgress()
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
        } else {
            viewState.showConnectionProblem()
        }
    }
}
