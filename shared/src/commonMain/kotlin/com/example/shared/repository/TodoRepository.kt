package com.example.shared.repository

import com.example.shared.data.model.Todo
import com.example.shared.domain.DataError
import com.example.shared.domain.Result


interface TodoRepository {

    suspend fun getTodos(): Result<List<Todo>, DataError.Remote>
}