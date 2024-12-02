package com.ae_health.data.di

import com.ae_health.data.repository.OrganizationRepositoryImpl
import com.ae_health.domain.repository.OrganizationRepository
import com.ae_health.domain.use_case.util.AppUseCases
import com.ae_health.domain.use_case.util.AppUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OrganizationsModule {

    @Binds
    abstract fun bindOrganizationRepository(organizationRepositoryImpl: OrganizationRepositoryImpl): OrganizationRepository

    @Binds
    abstract fun bindAppUseCases(appUseCasesImpl: AppUseCasesImpl): AppUseCases
}
