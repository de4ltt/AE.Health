package com.ae_health.domain.model

data class AppointmentDomain(
    val appointmentId: Int? = null,
    val dateTime: String,
    val organization: OrganizationDomain,
    val room: String? = null,
    val specialist: String? = null,
    val comment: String? = null
)