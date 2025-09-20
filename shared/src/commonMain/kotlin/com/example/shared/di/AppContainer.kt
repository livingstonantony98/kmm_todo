package com.example.shared.di

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.shared.networking.KtorRemoteBookDataSource
import com.example.shared.networking.RemoteBookDataSource
import com.example.shared.repository.TodoRepository
import com.example.shared.repository.TodoRepositoryImpl
import com.example.shared.ui.MyViewmodel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }

class AppContainer(
) {


    val todoRepository: TodoRepository by lazy {
        TodoRepositoryImpl(
            commonCreateApi()
        )
    }

    val todoViewModelFactory  = viewModelFactory {
        initializer {
            MyViewmodel(todoRepository)
        }
    }
    internal fun commonCreateApi(): RemoteBookDataSource = KtorRemoteBookDataSource(
        httpClient = HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        },
    )

}