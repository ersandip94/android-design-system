package com.codewithsandip.domain.usecase

import com.codewithsandip.domain.common.AppError
import com.codewithsandip.domain.common.appErrorOrNull
import com.codewithsandip.domain.fake.FakeDestinationRepository
import com.codewithsandip.domain.model.Destination
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DestinationUseCasesTest {

    private fun destination(id: String) = Destination(
        id = id,
        name = "Kyoto",
        country = "Japan",
        summary = "Temples and gardens.",
        rating = 4.8,
        priceFromUsd = 900,
        tags = listOf("Culture"),
    )

    @Test
    fun observe_emitsRepositoryList() = runTest {
        val items = listOf(destination("1"), destination("2"))
        val observe = ObserveDestinationsUseCase(FakeDestinationRepository(items))

        assertEquals(items, observe().first())
    }

    @Test
    fun getDetail_returnsSuccessForKnownId() = runTest {
        val items = listOf(destination("1"), destination("2"))
        val getDetail = GetDestinationDetailUseCase(FakeDestinationRepository(items))

        assertEquals(Result.success(destination("2")), getDetail("2"))
    }

    @Test
    fun getDetail_returnsNotFoundForUnknownId() = runTest {
        val getDetail = GetDestinationDetailUseCase(FakeDestinationRepository(listOf(destination("1"))))

        assertEquals(AppError.NotFound, getDetail("missing").appErrorOrNull())
    }
}
