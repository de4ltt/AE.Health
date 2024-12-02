package com.ae_health.data.mapper

import com.ae_health.data.local.model.Organizations
import com.ae_health.data.remote.model.Organization

fun Organization.toEntity(): Organizations =
    Organizations(
        organizationId = id?.toLong(),
        name = name,
        type = type,
        workSchedule = openingHours,
        address = address,
        phoneNumber = phone,
        description = additionalInfo,
        rating = rating,
        lat = latitude,
        lon = longitude,
        website = website,
        imageUrl = imageUrl
    )