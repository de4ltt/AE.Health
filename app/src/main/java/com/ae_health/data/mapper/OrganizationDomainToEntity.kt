package com.ae_health.data.mapper

import com.ae_health.data.local.model.Organizations
import com.ae_health.domain.model.OrganizationDomain

fun OrganizationDomain.toEntity() = Organizations(
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