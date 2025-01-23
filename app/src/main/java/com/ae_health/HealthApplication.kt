package com.ae_health

import android.app.Application
import androidx.room.Room
import com.ae_health.data.local.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HealthApplication: Application() {

    private lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "health.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}