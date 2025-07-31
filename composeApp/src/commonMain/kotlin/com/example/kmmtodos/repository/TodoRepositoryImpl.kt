package com.example.kmmtodos.repository

import com.example.kmmtodos.data.model.Todo
import com.example.kmmtodos.data.model.TodoDTO
import com.example.kmmtodos.domain.DataError
import com.example.kmmtodos.domain.Result
import com.example.kmmtodos.domain.map
import com.example.kmmtodos.networking.RemoteBookDataSource


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