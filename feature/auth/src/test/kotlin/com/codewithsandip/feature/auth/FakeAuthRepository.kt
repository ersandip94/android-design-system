package com.codewithsandip.feature.auth

import com.codewithsandip.domain.model.Credentials
import com.codewithsandip.domain.model.User
import com.codewithsandip.domain.repository.AuthRepository

/** Configurable fake for auth ViewModel tests. */
class FakeAuthRepository(
    var loginResult: Result<User> = Result.success(User("u1", "demo@codewithsandip.com", "Demo")),
    var signupResult: Result<User> = Result.success(User("u2", "new@codewithsandip.com", "New")),
) : AuthRepository {

    var lastLoginCredentials: Credentials? = null
    var lastSignupName: String? = null

    override suspend fun login(credentials: Credentials): Result<User> {
        lastLoginCredentials = credentials
        return loginResult
    }

    override suspend fun signup(name: String, credentials: Credentials): Result<User> {
        lastSignupName = name
        return signupResult
    }
}
