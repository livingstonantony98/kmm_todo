package com.example.kmmtodos

import androidx.compose.ui.window.ComposeUIViewController
import com.example.kmmtodos.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }