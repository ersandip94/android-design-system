package com.codewithsandip.feature.home.detail

import androidx.lifecycle.SavedStateHandle
import com.codewithsandip.domain.model.Destination
import com.codewithsandip.domain.usecase.GetDestinationDetailUseCase
import com.codewithsandip.feature.home.FakeDestinationRepository
import com.codewithsandip.feature.home.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class DestinationDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val kyoto =
        Destination("kyoto", "Kyoto", "Japan", "Temples and gardens.", 4.8, 900, listOf("Culture"))

    private fun viewModel(id: String, repo: FakeDestinationRepository) =
        DestinationDetailViewModel(
            savedStateHandle = SavedStateHandle(mapOf(DestinationDetailViewModel.ARG_ID to id)),
            getDestinationDetail = GetDestinationDetailUseCase(repo),
        )

    @Test
    fun knownId_loadsContent() = runTest {
        val vm = viewModel("kyoto", FakeDestinationRepository(listOf(kyoto)))

        assertEquals(DestinationDetailUiState.Content(kyoto), vm.uiState.value)
    }

    @Test
    fun unknownId_loadsError() = runTest {
        val vm = viewModel("atlantis", FakeDestinationRepository(listOf(kyoto)))

        assertTrue(vm.uiState.value is DestinationDetailUiState.Error)
    }
}
