package com.ae_health.presentation.model.util

import java.time.LocalTime

data class DaySchedule(
    val isActive: Boolean = false,
    val start: LocalTime = LocalTime.of(0, 0),
    val end: LocalTime = LocalTime.of(23, 59),
    val breaks: List<Pair<LocalTime, LocalTime>> = emptyList()
)