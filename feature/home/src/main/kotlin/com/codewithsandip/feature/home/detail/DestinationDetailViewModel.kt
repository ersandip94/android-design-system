package com.codewithsandip.feature.home.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithsandip.domain.usecase.GetDestinationDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinationDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDestinationDetail: GetDestinationDetailUseCase,
) : ViewModel() {

    private val destinationId: String = checkNotNull(savedStateHandle[ARG_ID]) {
        "DestinationDetail requires a '$ARG_ID' argument"
    }

    private val _uiState = MutableStateFlow<DestinationDetailUiState>(DestinationDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        _uiState.value = DestinationDetailUiState.Loading
        viewModelScope.launch {
            getDestinationDetail(destinationId)
                .onSuccess { _uiState.value = DestinationDetailUiState.Content(it) }
                .onFailure { _uiState.value = DestinationDetailUiState.Error("Couldn't load this destination.") }
        }
    }

    companion object {
        const val ARG_ID = "id"
    }
}
