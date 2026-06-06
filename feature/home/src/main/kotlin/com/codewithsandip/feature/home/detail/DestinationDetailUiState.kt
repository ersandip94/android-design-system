package com.codewithsandip.feature.home.detail

import com.codewithsandip.domain.model.Destination

/** UI state for the destination detail screen. */
sealed interface DestinationDetailUiState {
    data object Loading : DestinationDetailUiState
    data class Content(val destination: Destination) : DestinationDetailUiState
    data class Error(val message: String) : DestinationDetailUiState
}
