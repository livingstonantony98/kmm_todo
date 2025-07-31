package com.example.kmmtodos

import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
actual fun getNativeResponse(input: Int): Int {
    return input + 10
}

interface NativeResponseProvider {
    fun getNativeResponse(input: Int): Int
}

lateinit var nativeResponseProvider: NativeResponseProvider