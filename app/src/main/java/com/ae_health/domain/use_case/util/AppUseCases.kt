package com.ae_health.domain.use_case.util

import com.ae_health.domain.use_case.AddAppointmentUseCase
import com.ae_health.domain.use_case.AddFavouriteUseCase
import com.ae_health.domain.use_case.AddHistoryUseCase
import com.ae_health.domain.use_case.DeleteAppointmentUseCase
import com.ae_health.domain.use_case.DeleteFavouriteUseCase
import com.ae_health.domain.use_case.GetAppointmentsUseCase
import com.ae_health.domain.use_case.GetFavouritesUseCase
import com.ae_health.domain.use_case.GetHistoryUseCase
import com.ae_health.domain.use_case.GetOrganizationsNearbyUseCase

interface AppUseCases {
    val AddAppointmentUseCase: AddAppointmentUseCase
    val AddHistoryUseCase: AddHistoryUseCase
    val DeleteAppointmentUseCase: DeleteAppointmentUseCase
    val GetAppointmentsUseCase: GetAppointmentsUseCase
    val GetFavouritesUseCase: GetFavouritesUseCase
    val GetHistoryUseCase: GetHistoryUseCase
    val GetOrganizationsNearbyUseCase: GetOrganizationsNearbyUseCase
    val AddFavouriteUseCase: AddFavouriteUseCase
    val DeleteFavouriteUseCase: DeleteFavouriteUseCase
}