package com.codewithsandip.feature.home.list

import com.codewithsandip.domain.model.Destination

/** UI state for the destinations list screen. */
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Content(val destinations: List<Destination>) : HomeUiState
    data object Empty : HomeUiState
    data class Error(val message: String) : HomeUiState
}
