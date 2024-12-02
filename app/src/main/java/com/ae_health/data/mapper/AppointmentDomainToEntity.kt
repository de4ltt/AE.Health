package com.ae_health.data.mapper

import com.ae_health.data.local.model.Appointments
import com.ae_health.domain.model.AppointmentDomain

fun AppointmentDomain.toEntity() = Appointments(
    appointmentId = appointmentId,
    dateTime = dateTime,
    organizationId = organization.organizationId,
    room = room,
    specialist = specialist,
    comment = comment
)