package com.ae_health.presentation.model

import com.ae_health.presentation.ui.cross_screen.util.OrganizationType

data class Organization(
    val organizationId: Long = -1,
    val title: String = "",
    val type: OrganizationType = OrganizationType.DEFAULT,
    val workSchedule: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val comment: String? = null,
    val rating: Float? = null,
    val lat: Float? = null,
    val lon: Float? = null,
    val website: String? = null,
    val imageUrl: String? = null
)