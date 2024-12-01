package com.ae_health.presentation.model

import com.ae_health.presentation.model.util.WorkSchedule
import com.ae_health.presentation.ui.cross_screen.util.OrganizationType

data class Organization(
    val title: String = "",
    val type: OrganizationType = OrganizationType.DEFAULT,
    val workSchedule: WorkSchedule? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val comment: String? = null,
    val rating: Float? = null,
    val lat: Float? = null,
    val lon: Float? = null
)
