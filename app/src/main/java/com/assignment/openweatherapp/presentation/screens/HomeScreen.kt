package com.assignment.openweatherapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assignment.openweatherapp.domain.models.CityWeatherResponse
import com.assignment.openweatherapp.domain.models.WeatherUiState
import com.assignment.openweatherapp.presentation.PlatformConstants
import com.assignment.openweatherapp.presentation.component.LoadingDialog
import com.assignment.openweatherapp.presentation.datastore.SettingsViewModel
import com.assignment.openweatherapp.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(settingsViewModel: SettingsViewModel, weatherViewModel: WeatherViewModel, onNavigate: (CityWeatherResponse) -> Unit) {

    val cityName by settingsViewModel.city.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val weatherUIState = weatherViewModel.uiState.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    Text("Home")
                } }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = { paddingValues ->

            when (val value = weatherUIState.value?.getContentIfNotHandled()) {
                is WeatherUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxWidth(0.90f)) {
                        LoadingDialog("Fetching City Weather")
                    }
                }
                is WeatherUiState.Success -> {
                    val weather = value.data
                    onNavigate(weather)
                }
                is WeatherUiState.Error -> {
                    val errorMessage = value.message
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = errorMessage,
                            actionLabel = "Dismiss"
                        )
                    }
                }
                is WeatherUiState.Empty -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Search For Your Favorite City Weather",
                            actionLabel = "Dismiss"
                        )
                    }
                   }

                else -> {

                }
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = cityName,
                        onValueChange = { settingsViewModel.saveCity(it) },
                        label = { Text("Enter city name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            if (cityName.isNotBlank()) {
                                weatherViewModel.fetchWeather(city = cityName.trim(), apiKey = PlatformConstants.API_KEY)
                            }
                        },
                        enabled = cityName.isNotBlank(),
                        modifier = Modifier.fillMaxWidth()
                    ) {

                       Text("Get City Weather")

                    }
                }


            }

        })
}