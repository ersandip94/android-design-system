package com.codewithsandip.data.repository

import com.codewithsandip.data.remote.MockDestinationApi
import com.codewithsandip.data.remote.dto.toDomain
import com.codewithsandip.domain.model.Destination
import com.codewithsandip.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DestinationRepositoryImpl @Inject constructor(
    private val api: MockDestinationApi,
) : DestinationRepository {

    override fun observeDestinations(): Flow<List<Destination>> =
        api.destinations().map { dtos -> dtos.map { it.toDomain() } }

    override suspend fun getDestination(id: String): Result<Destination> = resultCatching {
        api.destination(id).toDomain()
    }
}
