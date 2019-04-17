package com.ltn.openweather.dagger.module

import android.content.Context
import androidx.room.Room
import com.ltn.openweather.Constants
import com.ltn.openweather.room.WeatherDB
import com.ltn.openweather.room.WeatherDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDB(context: Context): WeatherDB {
        return Room.databaseBuilder(context, WeatherDB::class.java, Constants.DB_NAME).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(weatherDB: WeatherDB): WeatherDao {
        return weatherDB.weatherDao()
    }
}