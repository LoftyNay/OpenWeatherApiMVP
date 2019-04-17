package com.ltn.openweather.dagger.module

import android.app.AlarmManager
import android.content.Context
import androidx.core.content.getSystemService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AlarmManagerModule {

    @Provides
    @Singleton
    fun provideAlarmManager(context: Context): AlarmManager {
        return context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
}