package com.ltn.openweather.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherFindByCityNameResponse(
    @SerializedName(value = "cod")
    var code: String,
    var count: Int,
    var message: String,
    var list: List<WeatherResponse>
)
