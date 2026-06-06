package com.codewithsandip.domain.repository

import com.codewithsandip.domain.model.Credentials
import com.codewithsandip.domain.model.User

/** Authentication operations. Implemented in the data layer. */
interface AuthRepository {
    suspend fun login(credentials: Credentials): Result<User>
    suspend fun signup(name: String, credentials: Credentials): Result<User>
}
