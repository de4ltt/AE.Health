package com.ae_health.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ae_health.data.local.model.Appointments
import com.ae_health.data.local.model.Favourites
import com.ae_health.data.local.model.History
import com.ae_health.data.local.model.Organizations
import com.ae_health.data.local.util.AppointmentWithOrganization
import com.ae_health.data.local.util.HistoryWithOrganizations
import kotlinx.coroutines.flow.Flow

@Dao
interface OrganizationDAO {

    @Query(
        """
    SELECT History.date AS date, Organizations.* 
    FROM History
    INNER JOIN Organizations 
    ON History.organizationId = Organizations.id
"""
    )
    fun getHistory(): Flow<List<HistoryWithOrganizations>>

    @Upsert
    suspend fun addOrganization(organizations: Organizations)

    @Query(
        """
    SELECT * FROM Organizations as org
    WHERE org.id IN (SELECT fav.organizationId FROM Favourites as fav)
    """
    )
    fun getFavourite(): Flow<List<Organizations>>

    @Upsert
    suspend fun addToHistory(history: History)

    @Upsert
    suspend fun addToFavourite(favourites: Favourites)

    @Delete
    suspend fun deleteFromFavourite(favourites: Favourites)

    @Upsert
    suspend fun addAppointment(appointments: Appointments)

    @Query(
        """
    SELECT * 
    FROM Appointments
    INNER JOIN Organizations 
    ON Organizations.id = Appointments.organizationId
"""
    )
    fun getAppointments(): Flow<List<AppointmentWithOrganization>>

    @Delete
    suspend fun deleteAppointment(appointments: Appointments)
}