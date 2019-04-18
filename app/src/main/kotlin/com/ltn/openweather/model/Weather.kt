package com.ltn.openweather.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey
    var id: Long,
    var name: String,
    var lat: Double,
    var lon: Double,
    var temp: Int,
    var country: String,
    var weatherIcon: String,
    var weatherDesc: String
)
