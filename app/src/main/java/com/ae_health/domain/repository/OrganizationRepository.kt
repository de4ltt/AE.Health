package com.ae_health.domain.repository

import com.ae_health.domain.model.AppointmentDomain
import com.ae_health.domain.model.OrganizationDomain
import kotlinx.coroutines.flow.Flow

interface OrganizationRepository {

    suspend fun searchOrganizations(
        query: String?,
        special: List<String>,
        amenities: List<String>,
        lat: Double,
        lon: Double,
        radius: Int = 300
    ): List<OrganizationDomain>

    suspend fun getHistory(): Flow<List<Pair<String, List<OrganizationDomain>>>>

    suspend fun getFavourite(): Flow<List<OrganizationDomain>>

    suspend fun addToHistory(organizationDomain: OrganizationDomain)

    suspend fun addToFavourite(organizationDomain: OrganizationDomain)

    suspend fun deleteFromFavourite(organizationDomain: OrganizationDomain)

    suspend fun addAppointment(appointmentDomain: AppointmentDomain)

    suspend fun deleteAppointment(appointmentDomain: AppointmentDomain)

    suspend fun getAppointments(): Flow<List<AppointmentDomain>>

}