package com.ltn.openweather.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ltn.openweather.App
import com.ltn.openweather.R
import com.ltn.openweather.ui.activities.main.presenter.MainActivityPresenter
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


class WeatherReceiver : BroadcastReceiver() {

    @Inject
    lateinit var eventBus: EventBus

    init {
        App.appComponent.inject(this)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(MainActivityPresenter.ACTION_UPDATE_WEATHER)) {
            eventBus.post(context?.resources?.getString(R.string.update_complete))
        }
    }
}