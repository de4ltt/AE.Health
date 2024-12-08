package com.ae_health.presentation

import android.Manifest
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ae_health.presentation.model.event.GPSEvent
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.ui.cross_screen.AddFavAppointBottomSheet
import com.ae_health.presentation.ui.cross_screen.DefaultScaffold
import com.ae_health.presentation.ui.screen.OrganizationInfoScreen
import com.ae_health.presentation.ui.theme.AEHealthTheme
import com.ae_health.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel: MainViewModel by viewModels()

    fun requestLocationPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }

    fun onPermissionGranted() {
        mainViewModel.fetchCurrentLocation(this)
        mainViewModel.onEvent(GPSEvent.PermissionGranted)
    }

    fun onPermissionDenied() {
        mainViewModel.onEvent(GPSEvent.PermissionDenied)
        Log.e("Permissions", "Location permission denied")
    }

    override fun onResume() {
        super.onResume()

        requestLocationPermission()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        requestLocationPermission()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.hideSystemUi(extraAction = {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        })
        setDisplayCutoutMode()

        setContent {

            AEHealthTheme {

                val screenUIState by mainViewModel.screenUIState.collectAsStateWithLifecycle()
                val gpsState by mainViewModel.gpsState.collectAsStateWithLifecycle()

                DefaultScaffold(
                    screenUIState = screenUIState,
                    gpsState = gpsState,
                    onEvent = mainViewModel::onEvent
                )

                OrganizationInfoScreen(
                    organization = screenUIState.shownOrganization,
                    isFavourite = screenUIState.favouriteOrganizations.contains(screenUIState.shownOrganization),
                    onHeartClick = {

                        val organization = screenUIState.shownOrganization
                        organization?.let {
                            if (screenUIState.favouriteOrganizations.contains(it))
                                mainViewModel.onEvent(ScreenUIEvent.DeleteFavourite(it))
                            else mainViewModel.onEvent(ScreenUIEvent.AddFavourite(it))
                        }
                    },
                    onBack = { mainViewModel.onEvent(ScreenUIEvent.IdleShowOrganization) }
                )

                val isInFav =
                    screenUIState.favouriteOrganizations.contains(screenUIState.addFavAppointOrganization)

                AddFavAppointBottomSheet(
                    organization = screenUIState.addFavAppointOrganization,
                    isInFav = isInFav,
                    onBack = { mainViewModel.onEvent(ScreenUIEvent.IdleSwitchFavAppointBar) },
                    onAddFavourite = {
                        mainViewModel.onEvent(
                            if (isInFav) ScreenUIEvent.DeleteFavourite(
                                it
                            ) else ScreenUIEvent.AddFavourite(it)
                        )
                    },
                    onAddAppointment = { mainViewModel.onEvent(ScreenUIEvent.AddAppointment(it)) }
                )
            }


        }


    }


    private fun Window.hideSystemUi(extraAction: (WindowInsetsControllerCompat.() -> Unit)? = null) {
        WindowInsetsControllerCompat(this, this.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            extraAction?.invoke(controller)
        }
    }

    private fun Activity.setDisplayCutoutMode() {
        when {
            Build.VERSION.SDK_INT < Build.VERSION_CODES.R -> {
                window.attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                window.attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
            }
        }
    }
}
