package com.ae_health.presentation.mapper

import com.ae_health.domain.model.AppointmentDomain
import com.ae_health.presentation.model.Appointment
import java.time.LocalDateTime

fun AppointmentDomain.toPresentation() = Appointment(
    appointmentId = appointmentId,
    dateTime = LocalDateTime.parse(dateTime),
    organization = organization.toPresentation(),
    room = room,
    specialist = specialist,
    comment = comment
)