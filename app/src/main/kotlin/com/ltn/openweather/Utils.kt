package com.ltn.openweather

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager


class Utils(val context: Context) {

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}