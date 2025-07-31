package com.example.kmmtodos.networking

import com.example.kmmtodos.data.model.TodoDTO
import com.example.kmmtodos.domain.DataError
import com.example.kmmtodos.domain.Result

interface RemoteBookDataSource {
    suspend fun getTodos(): Result<List<TodoDTO>, DataError.Remote>
}