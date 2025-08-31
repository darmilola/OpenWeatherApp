package com.assignment.openweatherapp

import com.assignment.openweatherapp.domain.repository.WeatherRepository
import com.assignment.openweatherapp.presentation.PlatformConstants
import com.assignment.openweatherapp.viewmodel.WeatherViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WeatherViewModelTest {

    @Test
    fun testWeather_Repository() = runBlocking {
        val client = HttpClient(Android)
        val repo = WeatherRepository(client)

        val result = repo.getWeather("Lagos", PlatformConstants.API_KEY)
        assertEquals("Lagos", result.name)
    }
}