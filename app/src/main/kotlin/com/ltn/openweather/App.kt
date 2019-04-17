package com.ltn.openweather

import android.app.Application
import com.ltn.openweather.dagger.ApplicationComponent
import com.ltn.openweather.dagger.DaggerApplicationComponent
import com.ltn.openweather.dagger.module.ContextModule

class App : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = buildDaggerComponent()
    }

    private fun buildDaggerComponent(): ApplicationComponent {
        return DaggerApplicationComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }
}