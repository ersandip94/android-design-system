package com.codewithsandip.data.remote

import com.codewithsandip.data.remote.dto.DestinationDto
import com.codewithsandip.domain.common.AppError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * In-memory stand-in for a remote destinations service. Serves the destinations bundled in
 * `mock/destinations.json` (deserialized once), with simulated latency.
 */
@Singleton
class MockDestinationApi @Inject constructor() {

    private val destinations: List<DestinationDto> by lazy {
        MockJson.read<List<DestinationDto>>("/mock/destinations.json")
    }

    fun destinations(): Flow<List<DestinationDto>> = flow {
        delay(MOCK_NETWORK_DELAY_MS)
        emit(destinations)
    }

    suspend fun destination(id: String): DestinationDto {
        delay(MOCK_NETWORK_DELAY_MS)
        return destinations.firstOrNull { it.id == id } ?: throw MockApiException(AppError.NotFound)
    }
}
