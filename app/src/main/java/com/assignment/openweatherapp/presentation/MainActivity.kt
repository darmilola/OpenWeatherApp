package com.assignment.openweatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assignment.openweatherapp.domain.models.WeatherModel
import com.assignment.openweatherapp.presentation.screens.DetailScreen
import com.assignment.openweatherapp.presentation.screens.HomeScreen
import com.assignment.openweatherapp.presentation.screens.Screen
import com.assignment.openweatherapp.viewmodel.LaunchViewModel
import com.assignment.openweatherapp.viewmodel.NavigationViewModel
import com.assignment.openweatherapp.viewmodel.WeatherViewModel
import com.assignment.openweatherapp.ui.theme.OpenWeatherAppTheme
import com.assignment.youverifytest.di.initKoin
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(){

    private val viewModel: LaunchViewModel by viewModels()
    private val navViewModel: NavigationViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        initKoin()
        val splash = installSplashScreen()
        splash.setKeepOnScreenCondition {
            !viewModel.isReady.value
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
                                HomeScreen(weatherViewModel = weatherViewModel, onNavigate = {
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