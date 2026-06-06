package com.codewithsandip.feature.auth

import com.codewithsandip.domain.common.AppError

/** One-shot events emitted by the auth ViewModels (consumed once by the screen). */
sealed interface AuthEvent {
    /** Login/signup succeeded; the host should navigate onward. */
    data object Authenticated : AuthEvent
}

/** Maps a typed [AppError] to a user-facing message for the auth screens. */
internal fun AppError?.toAuthMessage(): String = when (this) {
    AppError.InvalidCredentials -> "Incorrect email or password."
    AppError.EmailTaken -> "That email is already registered."
    AppError.Network -> "Network problem. Please check your connection and try again."
    else -> "Something went wrong. Please try again."
}
