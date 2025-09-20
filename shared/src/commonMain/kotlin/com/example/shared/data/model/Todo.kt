package com.example.shared.data.model

data class Todo(
    val id: Int,
    val userId: Int,
    val title: String,
    val isCompleted: Boolean // Renamed for clarity in domain
)