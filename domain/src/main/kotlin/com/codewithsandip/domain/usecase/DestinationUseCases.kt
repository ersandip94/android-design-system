package com.codewithsandip.domain.usecase

import com.codewithsandip.domain.model.Destination
import com.codewithsandip.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow

/** Streams the list of destinations. */
class ObserveDestinationsUseCase(private val repository: DestinationRepository) {
    operator fun invoke(): Flow<List<Destination>> = repository.observeDestinations()
}

/** Fetches a single destination by id. */
class GetDestinationDetailUseCase(private val repository: DestinationRepository) {
    suspend operator fun invoke(id: String): Result<Destination> = repository.getDestination(id)
}
