package com.ae_health.presentation.util

import android.Manifest
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.ae_health.presentation.MainActivity
import com.ae_health.presentation.model.event.GPSEvent
import com.ae_health.presentation.viewmodel.MainViewModel

class LocationPermit(
    private val mainViewModel: MainViewModel,
    private val activity: MainActivity
) {

    private val requestPermissionLauncher = with(activity) {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                onPermissionGranted()
            } else {
                onPermissionDenied()
            }
        }
    }

    fun requestLocationPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    fun onPermissionGranted() {
        mainViewModel.fetchCurrentLocation(activity)
        mainViewModel.onEvent(GPSEvent.PermissionGranted)
    }

    private fun onPermissionDenied() {
        mainViewModel.onEvent(GPSEvent.PermissionDenied)
        Log.e("Permissions", "Location permission denied")
    }
}