package com.ae_health.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ae_health.presentation.ui.screen.FavouritesScreen
import com.ae_health.presentation.ui.screen.HistoryScreen
import com.ae_health.presentation.ui.screen.HomeScreen
import com.ae_health.presentation.ui.screen.OrganizationInfoScreen
import com.ae_health.presentation.ui.screen.ScheduleScreen

object Routes {
    object HOME
    object FAVOURITES
    object HISTORY
    object SCHEDULE
    object ORGANIZATION_INFO
}

@Composable
fun Navigation() {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Routes.HOME) {
        composable<Routes.HOME> {
            HomeScreen(
                navHostController = navHostController
            )
        }
        composable<Routes.FAVOURITES> {
            FavouritesScreen(
                navHostController = navHostController
            )
        }
        composable<Routes.HISTORY> {
            HistoryScreen(
                navHostController = navHostController
            )
        }
        composable<Routes.SCHEDULE> {
            ScheduleScreen(
                navHostController = navHostController
            )
        }
        composable<Routes.ORGANIZATION_INFO> {
            OrganizationInfoScreen(
                navHostController = navHostController
            )
        }
    }
}

