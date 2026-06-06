package com.codewithsandip.feature.home.list

import app.cash.turbine.test
import com.codewithsandip.domain.model.Destination
import com.codewithsandip.domain.usecase.ObserveDestinationsUseCase
import com.codewithsandip.feature.home.FakeDestinationRepository
import com.codewithsandip.feature.home.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun destination(id: String) =
        Destination(id, "Kyoto", "Japan", "Temples and gardens.", 4.8, 900, listOf("Culture"))

    /** Returns the first non-Loading state (the initial Loading may be conflated under the test dispatcher). */
    private suspend fun app.cash.turbine.ReceiveTurbine<HomeUiState>.awaitResolved(): HomeUiState {
        val first = awaitItem()
        return if (first is HomeUiState.Loading) awaitItem() else first
    }

    @Test
    fun loadsDestinations_intoContent() = runTest {
        val items = listOf(destination("1"), destination("2"))
        val vm = HomeViewModel(ObserveDestinationsUseCase(FakeDestinationRepository(items)))

        vm.uiState.test {
            assertEquals(HomeUiState.Content(items), awaitResolved())
        }
    }

    @Test
    fun emptyList_yieldsEmptyState() = runTest {
        val vm = HomeViewModel(ObserveDestinationsUseCase(FakeDestinationRepository(emptyList())))

        vm.uiState.test {
            assertEquals(HomeUiState.Empty, awaitResolved())
        }
    }
}
