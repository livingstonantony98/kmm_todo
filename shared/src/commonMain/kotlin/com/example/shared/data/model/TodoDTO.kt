package com.example.shared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TodoDTO(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)