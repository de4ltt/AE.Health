package com.ae_health.presentation.model.event

sealed class GPSEvent {

    data object PermissionGranted: GPSEvent()
    data object PermissionDenied: GPSEvent()
}