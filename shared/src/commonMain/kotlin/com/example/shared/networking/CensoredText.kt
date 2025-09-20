package com.example.shared.networking

import kotlinx.serialization.Serializable

@Serializable
data class CensoredText(
    val result: String
)