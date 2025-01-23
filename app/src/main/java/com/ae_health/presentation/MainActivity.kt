package com.ae_health.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.ui.cross_screen.AddFavAppointBottomSheet
import com.ae_health.presentation.ui.cross_screen.DefaultScaffold
import com.ae_health.presentation.ui.screen.OrganizationInfoScreen
import com.ae_health.presentation.ui.theme.AEHealthTheme
import com.ae_health.presentation.util.LocationPermit
import com.ae_health.presentation.util.hideSystemUI
import com.ae_health.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val locationPermit = LocationPermit(mainViewModel, this)

    override fun onResume() {
        super.onResume()

        locationPermit.requestLocationPermission()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        hideSystemUI()

        locationPermit.requestLocationPermission()

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
}
