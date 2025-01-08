package com.sopt.withsuhyeon

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WithSuhyeonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}