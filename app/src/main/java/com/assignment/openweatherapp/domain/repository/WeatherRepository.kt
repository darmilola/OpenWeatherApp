package com.assignment.openweatherapp.domain.repository

import com.assignment.openweatherapp.domain.models.CityWeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherRepository(private val client: HttpClient) {

    suspend fun getWeather(city: String, apiKey: String): CityWeatherResponse {
        return client.get("https://api.openweathermap.org/data/2.5/weather") {
            url {
                parameters.append("q", city)
                parameters.append("appid", apiKey)
            }
        }.body()
    }
}