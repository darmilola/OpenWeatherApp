package com.assignment.openweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.assignment.openweatherapp.domain.models.HttpStatus
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

    private val _uiState = MutableLiveData<Event<WeatherUiState>>()

    val uiState: LiveData<Event<WeatherUiState>> = _uiState


    fun fetchWeather(city: String, apiKey: String) {
        _uiState.value = Event(WeatherUiState.Loading)
        viewModelScope.launch {
            try {
                val result = repository.getWeather(city, apiKey)
                when (HttpStatus.fromCode(result.cod)) {
                    HttpStatus.OK -> {
                        _uiState.value = Event(WeatherUiState.Success(result))
                    }
                    HttpStatus.NOT_FOUND -> {
                        _uiState.value = Event(WeatherUiState.Error("City Not Found"))
                    }
                    else -> {
                        _uiState.value = Event(WeatherUiState.Error("Error Occurred Please Try Again"))
                    }
                }
            } catch (e: Exception) {
                _uiState.value = Event(WeatherUiState.Error(e.message!!))
            }
        }
    }
}
