package com.example.shared.repository

import com.example.shared.data.model.Todo
import com.example.shared.data.model.TodoDTO
import com.example.shared.domain.DataError
import com.example.shared.networking.RemoteBookDataSource

import com.example.shared.domain.Result
import com.example.shared.domain.map

class TodoRepositoryImpl(private val apiService: RemoteBookDataSource) : TodoRepository {

    override suspend fun getTodos(): Result<List<Todo>, DataError.Remote> {
        return apiService.getTodos().map { todoDTOS -> todoDTOS.map { it
            .toDomainModel()} }

    }
}

fun TodoDTO.toDomainModel(): Todo {
    return Todo(
        id = this.id,
        userId = this.userId,
        title = this.title,
        isCompleted = this.completed // Naming convention difference
    )
}