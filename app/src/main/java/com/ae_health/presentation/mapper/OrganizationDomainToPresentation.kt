package com.ae_health.presentation.mapper

import com.ae_health.domain.model.OrganizationDomain
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.ui.cross_screen.util.OrganizationType
import com.ae_health.presentation.ui.cross_screen.util.organizationType

fun OrganizationDomain.toPresentation() = Organization(
    organizationId = organizationId,
    title = name ?: "",
    type = type?.organizationType ?: OrganizationType.DEFAULT,
    workSchedule = workSchedule,
    address = address,
    phoneNumber = phoneNumber,
    comment = description,
    rating = rating?.toFloat(),
    lat = lat?.toFloat(),
    lon = lon?.toFloat(),
    website = website,
    imageUrl = imageUrl
)