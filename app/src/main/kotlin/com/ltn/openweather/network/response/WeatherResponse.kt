package com.ltn.openweather.network.response

import com.google.gson.annotations.Expose

data class WeatherResponse(
    var clouds: Clouds,
    var coord: Coord,
    var dt: Int,
    var id: Long,
    var main: Main,
    var name: String,
    @Expose
    var rain: Any?,
    @Expose
    var snow: Any?,
    var sys: Sys,
    var weather: List<Weather>,
    var wind: Wind
) {
    data class Wind(
        var deg: Double,
        var speed: Double
    )

    data class Weather(
        var description: String,
        var icon: String,
        var id: Int,
        var main: String
    )

    data class Sys(
        var country: String
    )

    data class Clouds(
        var all: Int
    )

    data class Coord(
        var lat: Double,
        var lon: Double
    )

    data class Main(
        var grnd_level: Double,
        var humidity: Int,
        var pressure: Double,
        var sea_level: Double,
        var temp: Double,
        var temp_max: Double,
        var temp_min: Double
    )
}