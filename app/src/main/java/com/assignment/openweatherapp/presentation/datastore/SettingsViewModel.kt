package com.assignment.openweatherapp.presentation.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.openweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SettingsViewModel(private val dataStore: DataStore<Preferences>) : ViewModel(){

    private val settingsRepository: SettingsRepository = SettingsRepository(dataStore)

    val city = settingsRepository.cityFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ""
    )


    fun saveCity(city: String) {
        viewModelScope.launch {
            settingsRepository.setCity(city)
        }
    }
}