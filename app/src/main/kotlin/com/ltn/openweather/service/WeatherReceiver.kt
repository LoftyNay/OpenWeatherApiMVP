package com.ltn.openweather.service

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.ltn.openweather.R
import com.ltn.openweather.ui.activities.presenter.MainActivityPresenter

class WeatherReceiver(var onReceiveListener: OnReceiveListener): BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(MainActivityPresenter.ACTION_UPDATE_WEATHER)) {
            Toast.makeText(context, context?.getString(R.string.update_complete), Toast.LENGTH_SHORT).show()
            onReceiveListener.onReceive()
        }
    }

    interface OnReceiveListener {
        fun onReceive()
    }
}