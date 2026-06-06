package com.codewithsandip.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Internal mock user record (includes the password) as stored in the bundled `users.json`.
 * The password never leaves the data layer — [toUserDto] strips it.
 */
@Serializable
internal data class MockUserDto(
    val id: String,
    val email: String,
    val displayName: String,
    val password: String,
)

internal fun MockUserDto.toUserDto(): UserDto = UserDto(
    id = id,
    email = email,
    displayName = displayName,
)
