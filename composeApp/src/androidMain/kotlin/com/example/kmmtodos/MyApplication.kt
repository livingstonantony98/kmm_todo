package com.example.kmmtodos

import android.app.Application
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelProvider
import com.example.shared.di.AppContainer
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()

        /* initKoin {
             androidContext(this@MyApplication)
         }*/

        container = AppContainer()
    }
}
val LocalAppContainer =
    staticCompositionLocalOf<AppContainer> { error("No AppContainer provided!") }