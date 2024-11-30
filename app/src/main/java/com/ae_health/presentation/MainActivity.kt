package com.ae_health.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ae_health.presentation.model.event.ScreenUIEvent
import com.ae_health.presentation.model.util.ScreenDestinations
import com.ae_health.presentation.ui.cross_screen.DefaultScaffold
import com.ae_health.presentation.ui.screen.FavouritesScreen
import com.ae_health.presentation.ui.screen.HistoryScreen
import com.ae_health.presentation.ui.screen.HomeScreen
import com.ae_health.presentation.ui.screen.ProfileScreen
import com.ae_health.presentation.ui.screen.ScheduleScreen
import com.ae_health.presentation.ui.theme.AEHealthTheme
import com.ae_health.presentation.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val mainViewModel: MainViewModel = viewModel()

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
                            }
                        )

                        ScreenDestinations.HOME -> HomeScreen(
                            modifier = modifier,
                            state = screenUIState,
                            onEvent = mainViewModel::onEvent
                        )

                        ScreenDestinations.PROFILE -> ProfileScreen()
                        ScreenDestinations.SCHEDULE -> ScheduleScreen()
                    }
                }
            }
        }
    }
}