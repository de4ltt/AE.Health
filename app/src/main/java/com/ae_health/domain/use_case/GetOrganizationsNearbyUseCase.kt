package com.ae_health.domain.use_case

import com.ae_health.domain.repository.OrganizationRepository
import javax.inject.Inject

class GetOrganizationsNearbyUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
) {

    suspend operator fun invoke(
        query: String? = null,
        special: List<String> = emptyList(),
        amenities: List<String> = emptyList(),
        lat: Double,
        lon: Double,
        radius: Int
    ) = organizationRepository.searchOrganizations(
        query = query,
        special = special,
        amenities = amenities,
        lat = lat,
        lon = lon,
        radius = radius
    )
}