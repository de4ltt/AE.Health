package com.ae_health.data.local.util

import androidx.room.Embedded
import com.ae_health.data.local.model.Appointments
import com.ae_health.data.local.model.Organizations

data class AppointmentWithOrganization(
    @Embedded val organization: Organizations,
    @Embedded val appointment: Appointments
)

data class HistoryWithOrganizations(
    val date: String,
    @Embedded val organization: Organizations
)