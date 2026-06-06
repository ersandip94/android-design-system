package com.codewithsandip.domain.validation

/** Result of validating a single form field. */
sealed interface ValidationResult {
    data object Valid : ValidationResult
    data class Invalid(val reason: String) : ValidationResult

    val isValid: Boolean get() = this is Valid

    /** The error message, or null when valid. */
    val errorOrNull: String? get() = (this as? Invalid)?.reason
}

/** Validates an email address. Pure — reused by use cases and ViewModels for inline errors. */
class ValidateEmail {
    operator fun invoke(email: String): ValidationResult {
        val trimmed = email.trim()
        return when {
            trimmed.isEmpty() -> ValidationResult.Invalid("Email is required")
            !isWellFormed(trimmed) -> ValidationResult.Invalid("Enter a valid email address")
            else -> ValidationResult.Valid
        }
    }

    private fun isWellFormed(email: String): Boolean {
        if (email.any { it.isWhitespace() }) return false
        val at = email.indexOf('@')
        if (at <= 0 || at != email.lastIndexOf('@')) return false
        val domain = email.substring(at + 1)
        val dot = domain.lastIndexOf('.')
        return dot > 0 && dot < domain.length - 1
    }
}

/** Validates a password against a minimum length. */
class ValidatePassword(private val minLength: Int = MIN_LENGTH) {
    operator fun invoke(password: String): ValidationResult = when {
        password.isEmpty() -> ValidationResult.Invalid("Password is required")
        password.length < minLength ->
            ValidationResult.Invalid("Password must be at least $minLength characters")
        else -> ValidationResult.Valid
    }

    companion object {
        const val MIN_LENGTH: Int = 8
    }
}
