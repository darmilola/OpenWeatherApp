package com.assignment.openweatherapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class WeatherModel(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class Wind(
    val speed: Double = 0.0,
    val deg: Int = -1,
    val gust: Double = 0.0
)


@Serializable
data class CityWeatherResponse(
    val cod: Int,
    val name: String,
    val timezone: Int,
    val weather: List<WeatherModel>,
    val wind: Wind){
    companion object {
        val EMPTY = CityWeatherResponse(
            cod = 404,
            name = "",
            weather = emptyList(),
            timezone = -1,
            wind = Wind()
        )
    }
}

