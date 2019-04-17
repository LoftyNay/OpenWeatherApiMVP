package com.ltn.openweather.dagger.module

import android.content.Context
import com.ltn.openweather.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideUtils(context: Context): Utils {
        return Utils(context)
    }
}