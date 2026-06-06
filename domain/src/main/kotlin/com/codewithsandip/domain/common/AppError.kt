package com.codewithsandip.domain.common

/**
 * Coarse, presentation-friendly error categories. Carried as the failure of a [kotlin.Result]
 * via [AppException], so layers exchange standard `Result<T>` while still conveying a typed error.
 */
enum class AppError { InvalidCredentials, EmailTaken, Network, NotFound, Unknown }

/** Exception that carries a typed [AppError]; the failure value of a `Result`. */
open class AppException(val error: AppError) : Exception(error.name)

/** The [AppError] of a failed [Result], or null when it succeeded or failed with a non-app error. */
fun Result<*>.appErrorOrNull(): AppError? = (exceptionOrNull() as? AppException)?.error

/** Builds a failed [Result] carrying [error]. */
fun appFailure(error: AppError): Result<Nothing> = Result.failure(AppException(error))
