package com.ae_health.presentation.mapper

import com.ae_health.domain.model.AppointmentDomain
import com.ae_health.presentation.model.Appointment

fun Appointment.toDomain() = AppointmentDomain(
    appointmentId = appointmentId,
    dateTime = dateTime.toString(),
    organization = organization.toDomain(),
    room = room,
    specialist = specialist,
    comment = comment
)