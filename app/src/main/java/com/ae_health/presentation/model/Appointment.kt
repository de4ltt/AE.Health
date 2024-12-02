package com.ae_health.presentation.model

import java.time.LocalDateTime

data class Appointment(
    val appointmentId: Int? = null,
    val dateTime: LocalDateTime,
    val organization: Organization,
    val room: String? = null,
    val specialist: String? = null,
    val comment: String? = null
)
