package com.ae_health.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ae_health.data.local.dao.OrganizationDAO
import com.ae_health.data.local.model.Appointments
import com.ae_health.data.local.model.Favourites
import com.ae_health.data.local.model.History
import com.ae_health.data.local.model.Organizations

@Database(
    entities = [
        Appointments::class,
        Favourites::class,
        History::class,
        Organizations::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun organizationsDAO(): OrganizationDAO
}