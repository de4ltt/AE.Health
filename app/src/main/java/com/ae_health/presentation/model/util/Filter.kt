package com.ae_health.presentation.model.util

import androidx.annotation.StringRes
import com.ae_health.R

data class Filter(
    @StringRes val titleRes: Int,
    val rusName: String? = null
)

val filters = listOf(
    Filter(R.string.open),
    Filter(R.string.polyclinic),
    Filter(R.string.hospital),
    Filter(R.string.female_health),
    Filter(R.string.spa),
    Filter(R.string.pharmacy),
    Filter(R.string.time_24_7)
)