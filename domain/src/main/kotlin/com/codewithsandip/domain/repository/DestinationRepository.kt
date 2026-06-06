package com.codewithsandip.domain.repository

import com.codewithsandip.domain.model.Destination
import kotlinx.coroutines.flow.Flow

/** Destination data access. Implemented in the data layer. */
interface DestinationRepository {
    fun observeDestinations(): Flow<List<Destination>>
    suspend fun getDestination(id: String): Result<Destination>
}
