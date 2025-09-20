package com.example.shared.networking


import io.ktor.client.HttpClient
import io.ktor.client.request.get

import com.example.shared.data.model.TodoDTO
import com.example.shared.data.safeCall
import com.example.shared.domain.DataError
import com.example.shared.domain.Result

class KtorRemoteBookDataSource(private val httpClient: HttpClient) : RemoteBookDataSource {
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    override suspend fun getTodos(): Result<List<TodoDTO>, DataError.Remote> {
        return safeCall<List<TodoDTO>> {
            httpClient.get(urlString = "$baseUrl/todos")
        }
    }


}