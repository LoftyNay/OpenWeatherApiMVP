package com.ltn.openweather.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}