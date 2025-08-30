package com.assignment.openweatherapp.presentation

import com.assignment.openweatherapp.domain.models.CityWeatherResponse

object Util {

    fun isValidCity(cityWeatherResponse: CityWeatherResponse): Boolean {
        return cityWeatherResponse.cod == 200
    }

}