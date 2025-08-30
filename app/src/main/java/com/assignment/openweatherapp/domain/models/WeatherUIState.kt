package com.assignment.openweatherapp.domain.models


sealed class WeatherUiState {
    data object Loading : WeatherUiState()
    data class Success(val data: CityWeatherResponse) : WeatherUiState()
    data class Error(val message: String, val throwable: Throwable? = null) : WeatherUiState()
    data object Empty : WeatherUiState()
}