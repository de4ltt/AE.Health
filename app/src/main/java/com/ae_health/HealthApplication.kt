package com.ae_health

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HealthApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}