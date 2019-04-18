package com.ltn.openweather.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ltn.openweather.model.Weather

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherDB: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}