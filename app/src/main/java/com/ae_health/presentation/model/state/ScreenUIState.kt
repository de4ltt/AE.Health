package com.ae_health.presentation.model.state

import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.util.Filter
import com.ae_health.presentation.model.util.ScreenDestinations
import java.time.LocalDate

data class ScreenUIState(
    val curDestination: ScreenDestinations = ScreenDestinations.HOME,

    val isSearchActive: Boolean = false,
    val searchBarInput: String = "",
    val curFilters: List<Filter> = emptyList(),

    val curBestOrganizations: List<Organization> = randomOrganization,
    val latestVisitedOrganizations: List<Organization> = emptyList(),
    val favouriteOrganizations: List<Organization> = emptyList(),
    val historyOfVisitedOrganizations: Map<LocalDate, List<Organization>> = emptyMap(),

    val shownOrganization: Organization? = null
)

val randomOrganization = listOf(
    Organization(
        title = "Организация 1"
    ),
    Organization(
        title = "Организация 2"
    ),
    Organization(
        title = "Организация 3"
    )
)