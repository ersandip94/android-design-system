package com.codewithsandip.data.repository

import com.codewithsandip.data.remote.MockDestinationApi
import com.codewithsandip.domain.common.AppError
import com.codewithsandip.domain.common.appErrorOrNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DestinationRepositoryImplTest {

    private fun repo() = DestinationRepositoryImpl(MockDestinationApi())

    @Test
    fun observe_emitsAllTenDestinationsMappedToDomain() = runTest {
        val destinations = repo().observeDestinations().first()

        assertEquals(10, destinations.size)
        assertEquals("Kyoto", destinations.first().name)
        assertTrue(destinations.all { it.country.isNotBlank() })
    }

    @Test
    fun getDestination_knownId_succeeds() = runTest {
        val result = repo().getDestination("santorini")

        assertTrue(result.isSuccess)
        assertEquals("Santorini", result.getOrNull()?.name)
    }

    @Test
    fun getDestination_unknownId_failsWithNotFound() = runTest {
        val result = repo().getDestination("atlantis")

        assertEquals(AppError.NotFound, result.appErrorOrNull())
    }
}
