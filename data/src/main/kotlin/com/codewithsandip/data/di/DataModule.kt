package com.codewithsandip.data.di

import com.codewithsandip.data.repository.AuthRepositoryImpl
import com.codewithsandip.data.repository.DestinationRepositoryImpl
import com.codewithsandip.domain.repository.AuthRepository
import com.codewithsandip.domain.repository.DestinationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Binds domain repository interfaces to their data-layer implementations. */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindDestinationRepository(impl: DestinationRepositoryImpl): DestinationRepository
}
