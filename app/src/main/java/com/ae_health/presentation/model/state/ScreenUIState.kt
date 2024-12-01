package com.ae_health.presentation.model.state

import com.ae_health.presentation.model.Organization
import com.ae_health.presentation.model.util.DaySchedule
import com.ae_health.presentation.model.util.Filter
import com.ae_health.presentation.model.util.ScreenDestinations
import com.ae_health.presentation.model.util.WorkSchedule
import com.ae_health.presentation.ui.cross_screen.util.OrganizationType
import java.time.LocalDate
import java.time.LocalTime

data class ScreenUIState(
    val curDestination: ScreenDestinations = ScreenDestinations.HOME,

    val isSearchActive: Boolean = false,
    val searchBarInput: String = "",
    val curFilters: List<Filter> = emptyList(),

    val curBestOrganizations: List<Organization> = randomOrganization,
    val latestVisitedOrganizations: List<Organization> = emptyList(),
    val favouriteOrganizations: List<Organization> = emptyList(),
    val historyOfVisitedOrganizations: Map<LocalDate, List<Organization>> = datedRandomOrg,

    val shownOrganization: Organization? = null,
    val addFavAppointOrganization: Organization? = null,

    val isAddAppointmentBar: Boolean = false,
    val isFavAppointBar: Boolean = false
)

val randomOrganization = listOf(
    Organization(
        title = "Organization №1 with a very-very-very long name so it wouldn't fit the line",
        type = OrganizationType.HOSPITAL,
        workSchedule = WorkSchedule(
            listOf(
                DaySchedule(
                    isActive = true,
                    start = LocalTime.of(8, 0),
                    end = LocalTime.of(17, 0)
                ),
                DaySchedule(
                    isActive = true,
                    start = LocalTime.of(8, 0),
                    end = LocalTime.of(17, 0)
                ),
                DaySchedule(
                    isActive = true,
                    start = LocalTime.of(8, 0),
                    end = LocalTime.of(17, 0)
                ),
                DaySchedule(
                    isActive = true,
                    start = LocalTime.of(8, 0),
                    end = LocalTime.of(17, 0)
                ),
                DaySchedule(
                    isActive = true,
                    start = LocalTime.of(8, 0),
                    end = LocalTime.of(17, 0)
                ),
                DaySchedule(
                    isActive = true,
                    start = LocalTime.of(8, 0),
                    end = LocalTime.of(17, 0)
                ),
                DaySchedule(
                    isActive = false,
                    start = LocalTime.of(8, 0),
                    end = LocalTime.of(17, 0)
                )
            )
        ),
        address = "г. Краснодар, ул. Селезнёва, д.34",
        phoneNumber = "+79788819289",
        comment = "Какое-то длинное описание. Как там... Lorem ipsum dolor... Я не пам'ятаю... Дiдусь старий вже...",
        rating = 1.2f,
        lat = 54.98317f,
        lon = 82.88911f
    ),
    Organization(
        title = "Organization №2",
        type = OrganizationType.PHARMACY,
        lat = 54.98317f,
        lon = 82.88911f
    ),
    Organization(
        title = "Organization №3",
        type = OrganizationType.SPA,
        lat = 54.98317f,
        lon = 82.88911f
    )
)

val datedRandomOrg = mapOf(
    Pair(LocalDate.now(), randomOrganization),
    Pair(LocalDate.now().minusDays(1), randomOrganization.slice(0..1)),
    Pair(LocalDate.now().minusDays(20), listOf(randomOrganization[0])),
    Pair(LocalDate.now().minusYears(1), listOf(randomOrganization[2]))
)