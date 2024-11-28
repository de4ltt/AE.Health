package com.ae_health.presentation.model

import com.ae_health.presentation.model.util.WorkSchedule
import com.ae_health.presentation.ui.cross_screen.OrganizationType

data class Organization(
    val title: String = "",
    val type: OrganizationType = OrganizationType.UNKNOWN,
    val workSchedule: WorkSchedule? = null,
    val address: String? = null,
    val phoneNumber: String? = null
)
