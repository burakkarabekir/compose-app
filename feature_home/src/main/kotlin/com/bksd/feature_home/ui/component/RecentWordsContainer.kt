package com.bksd.feature_home.ui.component

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bksd.feature_home.ui.model.RecentWordUi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RecentWordsContainer(
    recentSearches: List<RecentWordUi>,
    onRecentSearchClick: (String) -> Unit,
    modifier: Modifier
) {
    Text(
        text = "Recent Searches",
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = modifier.height(16.dp))
    FlowRow(modifier = modifier) {
        recentSearches.forEach { recent ->
            RecentSearchItem(
                text = recent.text,
                onClick = { onRecentSearchClick(recent.text) },
                modifier = modifier
            )
        }
    }
}