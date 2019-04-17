package com.ltn.openweather.base

import android.content.Context
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.ltn.openweather.ui.fragments.view.AddCityFragmentView

abstract class BasePresenter<mvp : MvpView> : MvpPresenter<mvp>() {
    abstract fun attach()
    abstract fun detach()
}