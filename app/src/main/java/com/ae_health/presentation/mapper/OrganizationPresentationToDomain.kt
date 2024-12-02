package com.ae_health.presentation.mapper

import com.ae_health.domain.model.OrganizationDomain
import com.ae_health.presentation.model.Organization

fun Organization.toDomain() = OrganizationDomain(
    organizationId = organizationId,
    name = title.ifEmpty { null },
    type = type.amenity,
    workSchedule = workSchedule,
    address = address,
    phoneNumber = phoneNumber,
    description = comment,
    rating = rating?.toDouble(),
    lat = lat?.toDouble(),
    lon = lon?.toDouble(),
    website = website,
    imageUrl = imageUrl
)