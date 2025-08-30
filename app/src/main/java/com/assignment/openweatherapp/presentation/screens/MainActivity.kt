package com.assignment.openweatherapp.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assignment.openweatherapp.presentation.datastore.SettingsViewModel
import com.assignment.openweatherapp.viewmodel.LaunchViewModel
import com.assignment.openweatherapp.viewmodel.NavigationViewModel
import com.assignment.openweatherapp.viewmodel.WeatherViewModel
import com.assignment.openweatherapp.ui.theme.OpenWeatherAppTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent{

    private val launch: LaunchViewModel by viewModels()
    private val navViewModel: NavigationViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        splash.setKeepOnScreenCondition {
            !launch.isReady.value
        }
        splash.setOnExitAnimationListener { provider ->
            val icon = provider.iconView

            icon.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .alpha(0f)
                .setDuration(3000L)
                .withEndAction { provider.remove() }
                .start()
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpenWeatherAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route,
                        modifier = Modifier.padding(paddingValues = innerPadding),

                        builder = {
                            composable(Screen.HomeScreen.route) {
                                HomeScreen(settingsViewModel = settingsViewModel, weatherViewModel = weatherViewModel, onNavigate = {
                                    navViewModel.setResponse(it)
                                    navController.navigate(Screen.DetailScreen.route)
                                })
                            }

                            composable(Screen.DetailScreen.route) {
                                val weatherResponse = navViewModel.weatherResponse.collectAsState().value
                                DetailScreen(weatherResponse, onBackPressed = {
                                    navController.navigate(Screen.HomeScreen.route)
                                })
                            }

                        })
                }
            }
        }
    }
}