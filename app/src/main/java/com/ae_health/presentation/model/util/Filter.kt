package com.ae_health.presentation.model.util

import androidx.annotation.StringRes
import com.ae_health.R

enum class FilterType {
    DISTANCE,
    AVAILABILITY,
    TYPE,
    SPECIAL
}

data class Filter(
    @StringRes val titleRes: Int,
    val engName: String,
    val type: FilterType
)

val filters = listOf(
    Filter(R.string.open, "open", FilterType.AVAILABILITY),
    Filter(R.string.polyclinic, "clinic", FilterType.TYPE),
    Filter(R.string.hospital, "hospital", FilterType.TYPE),
    Filter(R.string.dentist, "dentist", FilterType.TYPE),
    Filter(R.string.blood_donation, "blood_donation", FilterType.TYPE),
    Filter(R.string.pharmacy, "pharmacy", FilterType.TYPE),
    Filter(R.string.time_24_7, "24/7", FilterType.AVAILABILITY),
    Filter(R.string.dispancer, "диспансер", FilterType.SPECIAL),
    Filter(R.string.laboratory, "laboratory", FilterType.TYPE),
    Filter(R.string.m150, "150", FilterType.DISTANCE),
    Filter(R.string.m500, "500", FilterType.DISTANCE),
    Filter(R.string.m1000, "1000", FilterType.DISTANCE),
    Filter(R.string.m2000, "2000", FilterType.DISTANCE)
)