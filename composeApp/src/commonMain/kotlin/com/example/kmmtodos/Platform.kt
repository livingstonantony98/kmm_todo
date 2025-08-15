package com.example.kmmtodos

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

