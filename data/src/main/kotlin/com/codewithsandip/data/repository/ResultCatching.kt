package com.codewithsandip.data.repository

import com.codewithsandip.domain.common.AppError
import com.codewithsandip.domain.common.AppException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Runs [block] and wraps it in a [Result]. Coroutine cancellation is rethrown; an [AppException]
 * (e.g. from the mock APIs) is preserved as the failure, and any other error becomes
 * [AppError.Unknown]. The single place data-layer exceptions become `Result` failures.
 */
internal inline fun <T> resultCatching(block: () -> T): Result<T> = try {
    Result.success(block())
} catch (c: CancellationException) {
    throw c
} catch (e: Throwable) {
    Result.failure(if (e is AppException) e else AppException(AppError.Unknown))
}
