package com.codewithsandip.feature.home.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.codewithsandip.domain.model.Destination
import com.codewithsandip.ds.component.badge.CWSBadge
import com.codewithsandip.ds.component.badge.CWSBadgeStatus
import com.codewithsandip.ds.component.button.CWSButton
import com.codewithsandip.ds.component.button.CWSButtonVariant
import com.codewithsandip.ds.component.chip.CWSChip
import com.codewithsandip.ds.component.chip.CWSChipVariant
import com.codewithsandip.ds.theme.CWSTheme
import com.codewithsandip.feature.home.HomeLightDarkPreview

/** Stateful destination detail screen. */
@Composable
fun DestinationDetailScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DestinationDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    DestinationDetailContent(uiState = uiState, onBack = onBack, modifier = modifier)
}

/** Stateless detail UI. */
@Composable
fun DestinationDetailContent(
    uiState: DestinationDetailUiState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiState) {
            DestinationDetailUiState.Loading -> CircularProgressIndicator()
            is DestinationDetailUiState.Error -> Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(uiState.message, style = MaterialTheme.typography.bodyLarge)
                CWSButton(text = "Go back", onClick = onBack, variant = CWSButtonVariant.Secondary)
            }
            is DestinationDetailUiState.Content -> DestinationDetail(uiState.destination, onBack)
        }
    }
}

@Composable
private fun DestinationDetail(destination: Destination, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = destination.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f),
            )
            CWSBadge(text = "★ ${destination.rating}", status = CWSBadgeStatus.Success)
        }
        Text(text = destination.country, style = MaterialTheme.typography.titleSmall)
        Text(text = destination.summary, style = MaterialTheme.typography.bodyLarge)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            destination.tags.forEach { tag ->
                CWSChip(label = tag, onClick = {}, variant = CWSChipVariant.Suggestion)
            }
        }
        Text(text = "From $${destination.priceFromUsd} per person", style = MaterialTheme.typography.titleMedium)
        CWSButton(
            text = "Back to destinations",
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            variant = CWSButtonVariant.Secondary,
        )
    }
}

@HomeLightDarkPreview
@Composable
private fun DestinationDetailContentPreview() {
    CWSTheme {
        DestinationDetailContent(
            uiState = DestinationDetailUiState.Content(
                Destination("kyoto", "Kyoto", "Japan", "Temples, gardens, and tea houses in Japan's old capital.", 4.8, 920, listOf("Culture", "History")),
            ),
            onBack = {},
        )
    }
}
