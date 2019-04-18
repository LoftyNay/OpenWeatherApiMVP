package com.ltn.openweather.dagger

import com.ltn.openweather.base.BaseInteractor
import com.ltn.openweather.dagger.module.*
import com.ltn.openweather.service.WeatherReceiver
import com.ltn.openweather.service.WeatherService
import com.ltn.openweather.ui.activities.gps.GpsActivity
import com.ltn.openweather.ui.activities.main.MainActivity
import com.ltn.openweather.ui.activities.main.presenter.MainActivityPresenter
import com.ltn.openweather.ui.fragments.CityAddDialogFragment
import com.ltn.openweather.ui.fragments.presenter.CityAddDialogFragmentPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [ContextModule::class, NetworkModule::class, InteractorsModule::class, UtilsModule::class, RoomModule::class, AlarmManagerModule::class, EventBusModule::class]
)
@Singleton
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(gpsActivity: GpsActivity)

    fun inject(cityAddDialogFragment: CityAddDialogFragment)

    fun inject(weatherService: WeatherService)

    fun inject(baseInteractor: BaseInteractor)

    fun inject(weatherReceiver: WeatherReceiver)

    fun inject(mainActivityPresenter: MainActivityPresenter)
    fun inject(cityAddDialogFragmentPresenter: CityAddDialogFragmentPresenter)
}