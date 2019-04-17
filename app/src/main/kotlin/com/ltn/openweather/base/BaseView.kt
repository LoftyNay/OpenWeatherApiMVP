package com.ltn.openweather.base

import com.arellomobile.mvp.MvpView

interface BaseView : MvpView {
    fun showConnectionProblem()
}