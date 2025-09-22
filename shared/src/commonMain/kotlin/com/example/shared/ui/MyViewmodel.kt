package com.example.shared.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.data.model.Todo
import com.example.shared.domain.onError
import com.example.shared.domain.onSuccess
import com.example.shared.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed interface UiState<out T> {
    object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}


class MyViewmodel(val todoRepository: TodoRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Todo>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Todo>>> = _uiState.asStateFlow()

    init {
        // Fetch data immediately when the ViewModel is created.
        fetchTodos()
    }

    override fun onCleared() {
        super.onCleared()
    }
    private fun fetchTodos() {
        // Set the state to Loading before starting the coroutine.
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                // Call the repository and handle the result.
                todoRepository.getTodos()
                    .onSuccess { data ->
                        // Update the state to Success with the received data.
                        _uiState.value = UiState.Success(data)
                        println("MyViewmodel: DATA: $data")
                    }
                    .onError { e ->
                        // Update the state to Error if an exception occurs.
                        _uiState.value = UiState.Error(e.toString())
                        println("MyViewmodel: ERROR: $e")
                    }
            } catch (e: Exception) {
                // Catch any other exceptions that might occur.
                _uiState.value = UiState.Error(e.toString())
                println("MyViewmodel: CATCH ERROR: $e")
            }
        }
    }
}

