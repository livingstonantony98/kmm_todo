package com.example.kmmtodos.di

import com.example.kmmtodos.data.HttpClientFactory
import com.example.kmmtodos.networking.KtorRemoteBookDataSource
import com.example.kmmtodos.networking.RemoteBookDataSource
import com.example.kmmtodos.repository.TodoRepository
import com.example.kmmtodos.repository.TodoRepositoryImpl
import com.example.kmmtodos.ui.MyViewmodel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::TodoRepositoryImpl).bind<TodoRepository>()

    viewModelOf(::MyViewmodel)
}