package com.ae_health.presentation.model.util

import androidx.annotation.StringRes
import com.ae_health.R

data class Filter(
    @StringRes val titleRes: Int,
    val rusName: String? = null
)

val filters = listOf(
    Filter(R.string.open),
    Filter(R.string.polyclinics),
    Filter(R.string.hospitals),
    Filter(R.string.female_health),
    Filter(R.string.spa),
    Filter(R.string.pharmacies),
    Filter(R.string.time_24_7)
)