package com.ae_health.data.di

import android.content.Context
import androidx.room.Room
import com.ae_health.data.local.AppDatabase
import com.ae_health.data.local.dao.OrganizationDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "diary_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideOrganizationsDAO(database: AppDatabase): OrganizationDAO =
        database.organizationsDAO()
}