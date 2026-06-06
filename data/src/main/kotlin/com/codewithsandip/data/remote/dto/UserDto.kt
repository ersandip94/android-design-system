package com.codewithsandip.data.remote.dto

import com.codewithsandip.domain.model.User

/** Wire representation of a user as returned by the (mock) auth API. */
data class UserDto(
    val id: String,
    val email: String,
    val displayName: String,
)

fun UserDto.toDomain(): User = User(
    id = id,
    email = email,
    displayName = displayName,
)
