package com.ae_health.presentation.model.state

import com.ae_health.presentation.model.util.ScreenDestinations

data class ScreenUIState(
    val curDestination: ScreenDestinations = ScreenDestinations.HOME,
    val searchBarInput: String = ""
)
