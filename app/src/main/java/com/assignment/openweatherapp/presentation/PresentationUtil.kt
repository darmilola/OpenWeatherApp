package com.assignment.openweatherapp.presentation

import com.assignment.openweatherapp.domain.models.CityWeatherResponse

object PresentationUtil {

    fun isValidCity(cityWeatherResponse: CityWeatherResponse): Boolean {
        return cityWeatherResponse.cod == 200
    }

}