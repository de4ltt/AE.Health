package com.ae_health.domain.use_case

import com.ae_health.domain.repository.OrganizationRepository
import javax.inject.Inject

class GetAppointmentsUseCase @Inject constructor(
    private val organizationRepository: OrganizationRepository
) {

    suspend operator fun invoke() =
        organizationRepository.getAppointments()
}