package com.ae_health.data.repository

import com.ae_health.data.local.dao.OrganizationDAO
import com.ae_health.data.local.model.Favourites
import com.ae_health.data.local.model.History
import com.ae_health.data.mapper.toDomain
import com.ae_health.data.mapper.toEntity
import com.ae_health.domain.model.AppointmentDomain
import com.ae_health.domain.model.OrganizationDomain
import com.ae_health.domain.repository.OrganizationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class OrganizationRepositoryImpl @Inject constructor(
    private val organizationDAO: OrganizationDAO
) : OrganizationRepository {

    override suspend fun searchOrganizations(
        amenities: List<String>,
        lat: Double,
        lon: Double,
        radius: Int
    ): List<OrganizationDomain> = fetchOrganizations(
        amenities = amenities,
        lat = lat,
        lon = lon,
        radius = radius
    ).map { it.toEntity().toDomain() }

    override suspend fun getHistory(): Flow<List<Pair<String, OrganizationDomain>>> =
        organizationDAO.getHistory().map { list ->
            list.map {
                Pair(it.first, it.second.toDomain())
            }
        }

    override suspend fun getFavourite(): Flow<List<OrganizationDomain>> =
        organizationDAO.getFavourite().toDomain()

    override suspend fun addToHistory(organizationDomain: OrganizationDomain) =
        organizationDAO.addToHistory(
            History(date = LocalDate.now().toString(), organizationId = organizationDomain.organizationId)
        )

    override suspend fun addToFavourite(organizationDomain: OrganizationDomain) =
        organizationDAO.addToFavourite(
            Favourites(organizationId = organizationDomain.organizationId)
        )

    override suspend fun deleteFromFavourite(organizationDomain: OrganizationDomain) =
        organizationDAO.deleteFromFavourite(
            Favourites(organizationId = organizationDomain.organizationId)
        )

    override suspend fun addAppointment(appointmentDomain: AppointmentDomain) = organizationDAO.addAppointment(
        appointmentDomain.toEntity()
    )

    override suspend fun deleteAppointment(appointmentDomain: AppointmentDomain) = organizationDAO.deleteAppointment(
        appointmentDomain.toEntity()
    )

    override suspend fun getAppointments(): Flow<List<AppointmentDomain>> = organizationDAO.getAppointments().map { list ->
        list.map { it.toDomain() }
    }
}