package com.ae_health.presentation.ui.cross_screen

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ae_health.presentation.MainActivity
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.state.GPSState
import com.ae_health.presentation.model.state.ScreenUIState
import com.ae_health.presentation.model.util.ScreenDestinations
import com.ae_health.presentation.ui.cross_screen.bar.BottomBar
import com.ae_health.presentation.ui.cross_screen.bar.TopBar
import com.ae_health.presentation.ui.screen.FavouritesScreen
import com.ae_health.presentation.ui.screen.HistoryScreen
import com.ae_health.presentation.ui.screen.HomeScreen
import com.ae_health.presentation.ui.screen.NoGPSScreen
import com.ae_health.presentation.ui.screen.ScheduleScreen
import com.ae_health.presentation.ui.theme.Dimens.DEFAULT_SPACING
import com.ae_health.presentation.ui.theme.Dimens.MEDIUM_SPACING
import com.ae_health.presentation.ui.theme.Dimens.TOP_PADDING
import kotlinx.coroutines.delay

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@Composable
fun DefaultScaffold(
    modifier: Modifier = Modifier,
    screenUIState: ScreenUIState,
    gpsState: GPSState,
    onEvent: (ScreenUIEvent) -> Unit,
) {

    val context = LocalContext.current

    fun isConnected(): Boolean {
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    var isLocationEnabled by remember { mutableStateOf(true) }

    val fetchLocation = MainActivity::onPermissionGranted

    LaunchedEffect(Unit) {
        while (gpsState.userPosition == null) {
            isLocationEnabled = isConnected()
            if (isLocationEnabled) fetchLocation(context as MainActivity)
            delay(1000)
        }
    }

    Crossfade(gpsState.userPosition == null, animationSpec = tween(1000)) {
        if (it)
            NoGPSScreen(
                modifier = Modifier.fillMaxSize(),
                permissionGranted = gpsState.isPermissionGranted,
                locationEnabled = isLocationEnabled,
            )
        else
            Column(
                modifier = modifier.padding(
                    top = TOP_PADDING,
                )
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(MEDIUM_SPACING)
                ) {

                    TopBar(
                        state = screenUIState,
                        onEvent = onEvent
                    )

                    AnimatedContent(
                        modifier = Modifier.wrapContentSize(),
                        targetState = screenUIState.curDestination,
                        transitionSpec = {
                            (fadeIn(tween(200)) + scaleIn(
                                tween(200),
                                0.8f
                            )).togetherWith(fadeOut(tween(200)) + scaleOut(tween(200), 0.8f))
                        },
                        label = ""
                    ) {

                        val modifier = Modifier
                            .weight(1f)
                            .padding(DEFAULT_SPACING)

                        when (it) {
                            ScreenDestinations.FAVOURITES -> FavouritesScreen(
                                modifier = modifier,
                                state = screenUIState,
                                onEvent = onEvent
                            )

                            ScreenDestinations.HISTORY -> HistoryScreen(
                                modifier = modifier,
                                state = screenUIState,
                                onShowOrganization = {
                                    onEvent(
                                        ScreenUIEvent.ShowOrganization(it)
                                    )
                                },
                                onShowFavAppointBar = {
                                    onEvent(
                                        ScreenUIEvent.SwitchFavAppointBar(it)
                                    )
                                }
                            )

                            ScreenDestinations.HOME -> HomeScreen(
                                modifier = modifier,
                                state = screenUIState,
                                onEvent = onEvent
                            )
                            /*
                                                    ScreenDestinations.PROFILE -> ProfileScreen()*/
                            ScreenDestinations.SCHEDULE -> ScheduleScreen(
                                modifier = modifier,
                                appointments = screenUIState.appointments,
                                showOrganization = {
                                    onEvent(
                                        ScreenUIEvent.ShowOrganization(
                                            it
                                        )
                                    )
                                }
                            )
                        }

                    }
                }

                BottomBar(
                    onEvent = onEvent,
                    curDestination = screenUIState.curDestination
                )
            }
    }
}