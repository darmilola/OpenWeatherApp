package com.assignment.youverifytest.di

import android.util.Log
import com.assignment.youverifytest.domain.httpClient
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module


fun initKoin(){
    stopKoin()
    startKoin {
        modules(networkModule)
    }
}

val networkModule = module {
    single { provideHttpClient() }
}

fun provideHttpClient(): HttpClient {
    return httpClient
}
