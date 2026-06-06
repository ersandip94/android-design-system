package com.codewithsandip.data.remote

import com.codewithsandip.data.remote.dto.MockUserDto
import com.codewithsandip.data.remote.dto.UserDto
import com.codewithsandip.data.remote.dto.toUserDto
import com.codewithsandip.domain.common.AppError
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

/**
 * In-memory stand-in for a remote auth service. Seeds its user store from the bundled
 * `mock/users.json`, simulates latency, and throws [MockApiException] for typed failures.
 * Singleton so users added via signup persist for the session.
 */
@Singleton
class MockAuthApi @Inject constructor() {

    private val users: MutableList<MockUserDto> =
        MockJson.read<List<MockUserDto>>("/mock/users.json").toMutableList()

    suspend fun login(email: String, password: String): UserDto {
        delay(MOCK_NETWORK_DELAY_MS)
        val record = users.firstOrNull { it.email.equals(email, ignoreCase = true) }
            ?: throw MockApiException(AppError.InvalidCredentials)
        if (record.password != password) throw MockApiException(AppError.InvalidCredentials)
        return record.toUserDto()
    }

    suspend fun signup(name: String, email: String, password: String): UserDto {
        delay(MOCK_NETWORK_DELAY_MS)
        if (users.any { it.email.equals(email, ignoreCase = true) }) {
            throw MockApiException(AppError.EmailTaken)
        }
        val record = MockUserDto(id = "u-${users.size + 1}", email = email, displayName = name, password = password)
        users.add(record)
        return record.toUserDto()
    }
}
