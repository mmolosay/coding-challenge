package com.leverx.challenge.core.di.data

import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

/**
 * Module of 'data' architectural layer.
 */
@Module(
    includes = [
        NetworkModule::class,
        GatewayModule::class,
        DataSourceModule::class,
    ]
)
@DisableInstallInCheck
interface DataModule