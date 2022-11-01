package com.leverx.challenge.core.di.domain

import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

/**
 * Module of 'domain' architectural layer.
 */
@Module(
    includes = [
        UseCaseModule::class,
    ]
)
@DisableInstallInCheck
interface DomainModule