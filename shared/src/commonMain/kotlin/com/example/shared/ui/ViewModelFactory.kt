package com.example.shared.ui

import androidx.lifecycle.viewmodel.MutableCreationExtras

fun creationExtras(block: MutableCreationExtras.() -> Unit) = MutableCreationExtras().apply(block)
