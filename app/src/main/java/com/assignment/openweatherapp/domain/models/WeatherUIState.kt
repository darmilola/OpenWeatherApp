package com.assignment.openweatherapp.domain.models

data class WeatherUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val weather: CityWeatherResponse = CityWeatherResponse.EMPTY,
    val errorMessage: String? = "",
    val isSuccess: Boolean = false,
    val isError: Boolean = false
)