package com.example.kmmtodos.repository

import com.example.kmmtodos.data.model.Todo
import com.example.kmmtodos.data.model.TodoDTO
import com.example.kmmtodos.domain.DataError
import com.example.kmmtodos.domain.Result


interface TodoRepository {

    suspend fun getTodos(): Result<List<Todo>, DataError.Remote>
}