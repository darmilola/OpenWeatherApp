package com.assignment.openweatherapp.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.assignment.openweatherapp.domain.repository.WeatherRepository
import com.assignment.openweatherapp.presentation.datastore.SettingsRepository
import com.assignment.openweatherapp.presentation.datastore.SettingsViewModel
import com.assignment.openweatherapp.domain.httpClient
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module



class MyWeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyWeatherApp)
            modules(listOf(networkModule,appModule, dataStoreModule))
        }
    }
}

val networkModule = module {
    single { provideHttpClient() }
    single { provideWeatherRepository(get()) }
}

val appModule = module {
    single { SettingsRepository(get()) }
    viewModel { SettingsViewModel(get()) }
}

val dataStoreModule = module {
    single<DataStore<Preferences>> { DataStoreSingleton.provide(get()) }
}

fun provideHttpClient(): HttpClient {
    return httpClient
}

fun provideWeatherRepository(httpClient: HttpClient): WeatherRepository {
    return WeatherRepository(httpClient)
}
