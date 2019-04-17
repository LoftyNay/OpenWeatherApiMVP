package com.ltn.openweather.dagger.module

import com.ltn.openweather.ui.fragments.interactor.CityAddDialogFragmentInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorsModule {

    @Provides
    fun provideAddCityFragmentInteractor(): CityAddDialogFragmentInteractor {
        return CityAddDialogFragmentInteractor()
    }
}