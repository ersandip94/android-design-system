package com.codewithsandip.data.repository

import com.codewithsandip.data.remote.MockAuthApi
import com.codewithsandip.data.remote.dto.toDomain
import com.codewithsandip.domain.model.Credentials
import com.codewithsandip.domain.model.User
import com.codewithsandip.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: MockAuthApi,
) : AuthRepository {

    override suspend fun login(credentials: Credentials): Result<User> = resultCatching {
        api.login(credentials.email, credentials.password).toDomain()
    }

    override suspend fun signup(name: String, credentials: Credentials): Result<User> = resultCatching {
        api.signup(name, credentials.email, credentials.password).toDomain()
    }
}
