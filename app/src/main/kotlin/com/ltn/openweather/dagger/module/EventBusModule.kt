package com.ltn.openweather.dagger.module

import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

@Module
class EventBusModule {

    @Provides
    @Singleton
    fun provideEventBus(): EventBus {
        return EventBus.getDefault()
    }
}