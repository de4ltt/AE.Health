package com.ae_health.data.mapper

import com.ae_health.data.local.model.Organizations
import com.ae_health.domain.model.OrganizationDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Organizations.toDomain() = OrganizationDomain(
    organizationId = organizationId,
    name = name,
    type = type,
    workSchedule = workSchedule,
    address = address,
    phoneNumber = phoneNumber,
    description = description,
    rating = rating,
    lat = lat,
    lon = lon,
    website = website,
    imageUrl = imageUrl
)

fun Flow<List<Organizations>>.toDomain() = this.map { list -> list.map { it.toDomain() } }