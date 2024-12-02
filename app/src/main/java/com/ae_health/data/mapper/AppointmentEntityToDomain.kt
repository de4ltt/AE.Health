package com.ae_health.data.mapper

import com.ae_health.data.local.model.Appointments
import com.ae_health.data.local.model.Organizations
import com.ae_health.domain.model.AppointmentDomain

fun Pair<Organizations, Appointments>.toDomain() = AppointmentDomain(
    appointmentId = second.appointmentId,
    dateTime = second.dateTime,
    organization = first.toDomain(),
    room = second.room,
    specialist = second.specialist,
    comment = second.comment
)