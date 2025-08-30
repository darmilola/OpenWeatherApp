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
    val cod: Int = 404,
    val name: String = "",
    val timezone: Int = -1,
    val weather: List<WeatherModel> = emptyList(),
    val wind: Wind = Wind())

