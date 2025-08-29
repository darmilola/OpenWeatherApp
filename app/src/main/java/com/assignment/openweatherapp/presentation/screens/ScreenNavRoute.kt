package com.assignment.openweatherapp.presentation.screens

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home")
    data object DetailScreen : Screen("detail")
}