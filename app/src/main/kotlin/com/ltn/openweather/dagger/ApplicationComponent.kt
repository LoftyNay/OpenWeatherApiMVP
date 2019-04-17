package com.ltn.openweather.dagger

import com.ltn.openweather.base.BaseInteractor
import com.ltn.openweather.dagger.module.*
import com.ltn.openweather.service.WeatherService
import com.ltn.openweather.ui.activities.MainActivity
import com.ltn.openweather.ui.fragments.CityAddDialogFragment
import com.ltn.openweather.ui.fragments.presenter.CityAddDialogFragmentPresenter
import com.ltn.openweather.ui.activities.presenter.MainActivityPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = arrayOf(
        ContextModule::class,
        NetworkModule::class,
        InteractorsModule::class,
        UtilsModule::class,
        RoomModule::class,
        AlarmManagerModule::class
    )
)
@Singleton
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(cityAddDialogFragment: CityAddDialogFragment)

    fun inject(weatherService: WeatherService)

    fun inject(baseInteractor: BaseInteractor)

    fun inject(mainActivityPresenter: MainActivityPresenter)
    fun inject(cityAddDialogFragmentPresenter: CityAddDialogFragmentPresenter)
}