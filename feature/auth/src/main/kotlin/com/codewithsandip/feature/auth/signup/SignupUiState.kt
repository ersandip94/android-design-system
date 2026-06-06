package com.codewithsandip.feature.auth.signup

/** Immutable UI state for the signup screen. */
data class SignupUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null,
) {
    val canSubmit: Boolean
        get() = name.isNotBlank() && email.isNotBlank() && password.isNotBlank() && !isSubmitting
}
