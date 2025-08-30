package com.assignment.openweatherapp.presentation.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.edit

class SettingsRepository(private val dataStore: DataStore<Preferences>) {

    val cityFlow: Flow<String> = dataStore.data.map { prefs ->
        prefs[PreferenceKeys.CITY] ?: ""
    }

    suspend fun setCity(city: String) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.CITY] = city
        }
    }
}