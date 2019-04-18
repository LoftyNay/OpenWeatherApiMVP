package com.ltn.openweather.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

abstract class BasePresenter<mvp : MvpView> : MvpPresenter<mvp>() {
    abstract fun attach()
    abstract fun detach()
}