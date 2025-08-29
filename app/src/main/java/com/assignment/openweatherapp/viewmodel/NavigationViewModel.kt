package com.assignment.openweatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.assignment.openweatherapp.domain.models.CityWeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NavigationViewModel : ViewModel() {
    private val _cityWeatherResponse = MutableStateFlow<CityWeatherResponse?>(null)
    val weatherResponse: StateFlow<CityWeatherResponse?> = _cityWeatherResponse

    fun setResponse(response: CityWeatherResponse) {
        _cityWeatherResponse.value = response
    }
}