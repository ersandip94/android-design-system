package com.codewithsandip.feature.home.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.codewithsandip.domain.model.Destination
import com.codewithsandip.ds.component.badge.CWSBadge
import com.codewithsandip.ds.component.badge.CWSBadgeStatus
import com.codewithsandip.ds.component.card.CWSCard
import com.codewithsandip.ds.component.chip.CWSChip
import com.codewithsandip.ds.component.chip.CWSChipVariant
import com.codewithsandip.ds.theme.CWSTheme
import com.codewithsandip.feature.home.HomeLightDarkPreview

/** Stateful destinations list screen. */
@Composable
fun DestinationListScreen(
    onDestinationClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    DestinationListContent(uiState = uiState, onDestinationClick = onDestinationClick, modifier = modifier)
}

/** Stateless list UI. */
@Composable
fun DestinationListContent(
    uiState: HomeUiState,
    onDestinationClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiState) {
            HomeUiState.Loading -> CircularProgressIndicator()
            HomeUiState.Empty -> Text("No destinations yet.", style = MaterialTheme.typography.bodyLarge)
            is HomeUiState.Error -> Text(uiState.message, style = MaterialTheme.typography.bodyLarge)
            is HomeUiState.Content -> LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(uiState.destinations, key = { it.id }) { destination ->
                    DestinationCard(destination = destination, onClick = { onDestinationClick(destination.id) })
                }
            }
        }
    }
}

@Composable
private fun DestinationCard(destination: Destination, onClick: () -> Unit) {
    CWSCard(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = destination.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f),
            )
            CWSBadge(text = "★ ${destination.rating}", status = CWSBadgeStatus.Success)
        }
        Text(text = destination.country, style = MaterialTheme.typography.bodySmall)
        Text(
            text = destination.summary,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            destination.tags.forEach { tag ->
                CWSChip(label = tag, onClick = {}, variant = CWSChipVariant.Suggestion)
            }
        }
        Text(text = "From $${destination.priceFromUsd}", style = MaterialTheme.typography.labelLarge)
    }
}

@HomeLightDarkPreview
@Composable
private fun DestinationListContentPreview() {
    CWSTheme {
        DestinationListContent(
            uiState = HomeUiState.Content(
                listOf(
                    Destination("kyoto", "Kyoto", "Japan", "Temples, gardens, and tea houses.", 4.8, 920, listOf("Culture", "History")),
                    Destination("banff", "Banff", "Canada", "Turquoise lakes and Rocky peaks.", 4.9, 850, listOf("Nature", "Hiking")),
                ),
            ),
            onDestinationClick = {},
        )
    }
}
