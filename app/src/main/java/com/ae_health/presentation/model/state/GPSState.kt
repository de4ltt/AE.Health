package com.ae_health.presentation.model.state

import android.location.Location

data class GPSState(
    val isPermissionGranted: Boolean = true,
    val userPosition: Location? = null
)
