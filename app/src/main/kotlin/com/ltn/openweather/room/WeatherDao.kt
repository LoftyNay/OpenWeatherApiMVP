package com.ltn.openweather.room

import androidx.room.*
import com.ltn.openweather.model.Weather

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getAll(): MutableList<Weather>

    @Query("DELETE FROM weather WHERE id = :id ")
    fun deleteWeatherCityById(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: Weather)

    @Update
    fun update(weather: Weather)
}