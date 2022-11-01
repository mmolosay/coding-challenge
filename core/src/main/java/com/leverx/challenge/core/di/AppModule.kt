package com.leverx.challenge.core.di

import com.leverx.challenge.core.di.data.DataModule
import com.leverx.challenge.core.di.domain.DomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Main application module to be installed into 'Application'.
 */
@Module(
    includes = [
        DataModule::class,
        DomainModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
interface AppModule