package com.example.shared.networking

import com.example.shared.data.model.TodoDTO
import com.example.shared.domain.DataError
import com.example.shared.domain.Result

interface RemoteBookDataSource {
    suspend fun getTodos(): Result<List<TodoDTO>, DataError.Remote>
}