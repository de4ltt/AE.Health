package com.ae_health.presentation.model.state

import com.ae_health.presentation.model.Appointment
import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.util.Filter
import com.ae_health.presentation.model.util.ScreenDestinations
import java.time.LocalDate

data class ScreenUIState(
    //lat, lon
    val isLocationPermissionGranted: Boolean = false,
    val userLocation: Pair<Double, Double>? = null,

    val curDestination: ScreenDestinations = ScreenDestinations.HOME,

    val isSearchActive: Boolean = false,
    val searchBarInput: String = "",
    val curFilters: List<Filter> = emptyList(),
    val foundOrganizations: List<Organization> = emptyList(),

    val curBestOrganizations: List<Organization> = emptyList(),
    val latestVisitedOrganizations: List<Organization> = emptyList(),
    val favouriteOrganizations: List<Organization> = emptyList(),
    val historyOfVisitedOrganizations: Map<LocalDate, List<Organization>> = emptyMap(),
    val appointments: List<Appointment> = emptyList(),

    val shownOrganization: Organization? = null,
    val addFavAppointOrganization: Organization? = null,

    val isAddAppointmentBar: Boolean = false,
    val isFavAppointBar: Boolean = false
)