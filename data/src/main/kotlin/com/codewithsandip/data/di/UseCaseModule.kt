package com.codewithsandip.data.di

import com.codewithsandip.domain.repository.AuthRepository
import com.codewithsandip.domain.repository.DestinationRepository
import com.codewithsandip.domain.usecase.GetDestinationDetailUseCase
import com.codewithsandip.domain.usecase.LoginUseCase
import com.codewithsandip.domain.usecase.ObserveDestinationsUseCase
import com.codewithsandip.domain.usecase.SignupUseCase
import com.codewithsandip.domain.validation.ValidateEmail
import com.codewithsandip.domain.validation.ValidatePassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Provides the annotation-free domain use cases. Keeping these `@Provides` here lets `:domain`
 * stay free of Hilt/`javax.inject`.
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase = LoginUseCase(repository)

    @Provides
    fun provideSignupUseCase(repository: AuthRepository): SignupUseCase = SignupUseCase(repository)

    @Provides
    fun provideObserveDestinationsUseCase(
        repository: DestinationRepository,
    ): ObserveDestinationsUseCase = ObserveDestinationsUseCase(repository)

    @Provides
    fun provideGetDestinationDetailUseCase(
        repository: DestinationRepository,
    ): GetDestinationDetailUseCase = GetDestinationDetailUseCase(repository)

    @Provides
    fun provideValidateEmail(): ValidateEmail = ValidateEmail()

    @Provides
    fun provideValidatePassword(): ValidatePassword = ValidatePassword()
}

