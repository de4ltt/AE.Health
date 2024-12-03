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
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.util.ScreenDestinations
import com.ae_health.presentation.ui.cross_screen.AddFavAppointBottomSheet
import com.ae_health.presentation.ui.cross_screen.DefaultScaffold
import com.ae_health.presentation.ui.screen.FavouritesScreen
import com.ae_health.presentation.ui.screen.HistoryScreen
import com.ae_health.presentation.ui.screen.HomeScreen
import com.ae_health.presentation.ui.screen.OrganizationInfoScreen
import com.ae_health.presentation.ui.screen.ScheduleScreen
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
    }

    fun onPermissionDenied() {
        Log.e("Permissions", "Location permission denied")
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

                DefaultScaffold(
                    screenUIState = screenUIState,
                    onEvent = mainViewModel::onEvent
                ) { modifier ->

                    when (screenUIState.curDestination) {
                        ScreenDestinations.FAVOURITES -> FavouritesScreen(
                            modifier = modifier,
                            state = screenUIState,
                            onEvent = mainViewModel::onEvent
                        )

                        ScreenDestinations.HISTORY -> HistoryScreen(
                            modifier = modifier,
                            state = screenUIState,
                            onShowOrganization = {
                                mainViewModel.onEvent(
                                    ScreenUIEvent.ShowOrganization(it)
                                )
                            },
                            onShowFavAppointBar = {
                                mainViewModel.onEvent(
                                    ScreenUIEvent.SwitchFavAppointBar(it)
                                )
                            }
                        )

                        ScreenDestinations.HOME -> HomeScreen(
                            modifier = modifier,
                            state = screenUIState,
                            onEvent = mainViewModel::onEvent
                        )
/*
                        ScreenDestinations.PROFILE -> ProfileScreen()*/
                        ScreenDestinations.SCHEDULE -> ScheduleScreen(
                            modifier = modifier,
                            appointments = screenUIState.appointments,
                            showOrganization = {
                                mainViewModel.onEvent(
                                    ScreenUIEvent.ShowOrganization(
                                        it
                                    )
                                )
                            }
                        )
                    }

                }

                OrganizationInfoScreen(
                    organization = screenUIState.shownOrganization,
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
