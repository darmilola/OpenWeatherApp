package com.assignment.openweatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.openweatherapp.domain.models.WeatherUiState
import com.assignment.openweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WeatherViewModel() : ViewModel(), KoinComponent {

    private val repository: WeatherRepository by inject()
    private val _uiState = MutableStateFlow(WeatherUiState())

    val uiState: StateFlow<WeatherUiState> = _uiState

    fun fetchWeather(city: String, apiKey: String) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            _uiState
            try {
                val result = repository.getWeather(city, apiKey)
                _uiState.update { it.copy(weather = result, isLoading = false, isSuccess = true) }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message, isLoading = false, isError = true, isSuccess = false) }
            }
        }
    }
}