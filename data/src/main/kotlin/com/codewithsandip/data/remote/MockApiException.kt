package com.codewithsandip.data.remote

import com.codewithsandip.domain.common.AppError
import com.codewithsandip.domain.common.AppException

/** Thrown by the mock APIs to signal a typed failure; surfaces as a `Result` failure. */
class MockApiException(error: AppError) : AppException(error)

/** Simulated network latency for the mock layer (ms). */
internal const val MOCK_NETWORK_DELAY_MS = 600L
