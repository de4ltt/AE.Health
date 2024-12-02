package com.ae_health.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ae_health.data.local.model.Appointments
import com.ae_health.data.local.model.Favourites
import com.ae_health.data.local.model.History
import com.ae_health.data.local.model.Organizations
import kotlinx.coroutines.flow.Flow

@Dao
interface OrganizationDAO {

    @Query(
        """SELECT 
        date,
        (SELECT * FROM Organizations WHERE History.organizationId = Organizations.organizationId LIMIT 1) FROM history"""
    )
    suspend fun getHistory(): Flow<List<Pair<String, Organizations>>>

    @Query(
        """
    SELECT * FROM Organizations 
    WHERE organizationId IN (SELECT organizationId FROM Favourites)
    """
    )
    suspend fun getFavourite(): Flow<List<Organizations>>

    @Upsert
    suspend fun addToHistory(history: History)

    @Upsert
    suspend fun addToFavourite(favourites: Favourites)

    @Delete
    suspend fun deleteFromFavourite(favourites: Favourites)

    @Upsert
    suspend fun addAppointment(appointments: Appointments)

    @Query("SELECT  (SELECT * FROM Organizations WHERE Organizations.organizationId = organizationId), * FROM Appointments")
    suspend fun getAppointments(): Flow<List<Pair<Organizations, Appointments>>>

    @Delete
    suspend fun deleteAppointment(appointments: Appointments)
}