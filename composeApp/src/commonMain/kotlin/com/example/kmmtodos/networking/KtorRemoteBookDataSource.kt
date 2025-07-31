package com.example.kmmtodos.networking

import com.example.kmmtodos.data.model.TodoDTO
import com.example.kmmtodos.data.safeCall
import com.example.kmmtodos.domain.DataError
import com.example.kmmtodos.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get


class KtorRemoteBookDataSource(private val httpClient: HttpClient) : RemoteBookDataSource {
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    override suspend fun getTodos(): Result<List<TodoDTO>, DataError.Remote> {
        return safeCall<List<TodoDTO>> {
            httpClient.get(urlString = "$baseUrl/todos")
        }
    }


}