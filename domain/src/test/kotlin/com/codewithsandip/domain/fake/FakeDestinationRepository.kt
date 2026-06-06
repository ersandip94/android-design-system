package com.codewithsandip.domain.fake

import com.codewithsandip.domain.common.AppError
import com.codewithsandip.domain.common.appFailure
import com.codewithsandip.domain.model.Destination
import com.codewithsandip.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/** Hand-written test double for [DestinationRepository]. */
class FakeDestinationRepository(
    private val destinations: List<Destination> = emptyList(),
    /** When set, [getDestination] returns this regardless of id. */
    var forcedDetailResult: Result<Destination>? = null,
) : DestinationRepository {

    override fun observeDestinations(): Flow<List<Destination>> = flowOf(destinations)

    override suspend fun getDestination(id: String): Result<Destination> {
        forcedDetailResult?.let { return it }
        val match = destinations.find { it.id == id }
        return if (match != null) Result.success(match) else appFailure(AppError.NotFound)
    }
}
