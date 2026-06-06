package com.codewithsandip.feature.home.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithsandip.domain.usecase.ObserveDestinationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    observeDestinations: ObserveDestinationsUseCase,
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = observeDestinations()
        .map { destinations ->
            if (destinations.isEmpty()) HomeUiState.Empty else HomeUiState.Content(destinations)
        }
        .catch { emit(HomeUiState.Error("Couldn't load destinations.")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = HomeUiState.Loading,
        )

    private companion object {
        const val STOP_TIMEOUT_MS = 5_000L
    }
}
