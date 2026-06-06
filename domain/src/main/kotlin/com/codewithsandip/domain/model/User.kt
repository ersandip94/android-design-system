package com.codewithsandip.domain.model

/** An authenticated user. */
data class User(
    val id: String,
    val email: String,
    val displayName: String,
)

/** Email + password pair submitted to the auth layer. */
data class Credentials(
    val email: String,
    val password: String,
)
