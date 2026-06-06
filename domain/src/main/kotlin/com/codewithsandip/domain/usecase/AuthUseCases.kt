package com.codewithsandip.domain.usecase

import com.codewithsandip.domain.model.Credentials
import com.codewithsandip.domain.model.User
import com.codewithsandip.domain.repository.AuthRepository

/** Logs a user in with the given [Credentials]. */
class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(credentials: Credentials): Result<User> =
        repository.login(credentials.copy(email = credentials.email.trim()))
}

/** Registers a new user, then signs them in. */
class SignupUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(name: String, credentials: Credentials): Result<User> =
        repository.signup(name.trim(), credentials.copy(email = credentials.email.trim()))
}
