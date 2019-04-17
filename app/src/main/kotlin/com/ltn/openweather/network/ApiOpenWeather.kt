package com.ltn.openweather.network

import com.ltn.openweather.network.response.WeatherResponse
import com.ltn.openweather.network.response.WeatherFindByCityNameResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiOpenWeather {

    //api.openweathermap.org/data/2.5/find?q=минск&lang=ru&units=metric&appid=29d8167071540dac233056e923e48469
    @GET("/data/2.5/find?lang=ru&units=metric&appid=29d8167071540dac233056e923e48469")
    fun findWeatherCityListByName(
        @Query("q") cityName: String
    ): Observable<WeatherFindByCityNameResponse>

    @GET("/data/2.5/weather?lang=ru&units=metric&appid=29d8167071540dac233056e923e48469")
    fun findWeatherCityById(
        @Query("id") id: Long
    ): Observable<WeatherResponse>
}