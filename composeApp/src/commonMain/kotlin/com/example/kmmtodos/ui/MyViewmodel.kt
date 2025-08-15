package com.example.kmmtodos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmtodos.data.model.Todo
import com.example.kmmtodos.domain.onError
import com.example.kmmtodos.domain.onSuccess
import com.example.kmmtodos.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.math.log

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}


class MyViewmodel(val todoRepository: TodoRepository) : ViewModel() {


    private val _state = MutableStateFlow(emptyList<Todo>())
    private val _todos = MutableStateFlow<UiState<List<Todo>>>(UiState.Loading)
//    val todos: StateFlow<UiState<List<Todo>>> = _todos.asStateFlow() // Ex

    val todos: StateFlow<UiState<List<Todo>>> = _todos.onStart {
        fetchBookDescription()
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _todos.value
        )

    private fun fetchBookDescription() {
        _todos.value = UiState.Loading
        viewModelScope.launch {
            todoRepository.getTodos()
                .onSuccess { data ->
                    _todos.value = UiState.Success(data)
                    _state.value = data
                    print("MyViewmodel:DATA: $data")
                }.onError { e ->
                    print("MyViewmodel:ERROR: $e")
                    _todos.value = UiState.Error(e.toString())
                }
        }
    }
}

