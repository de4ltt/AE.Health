package com.ae_health.domain.model

data class OrganizationDomain(
    val organizationId: Long,
    val name: String? = null,
    val type: String? = null,
    val workSchedule: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val description: String? = null,
    val rating: Double? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val website: String? = null,
    val imageUrl: String? = null
)