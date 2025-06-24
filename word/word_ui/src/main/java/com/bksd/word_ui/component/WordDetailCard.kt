package com.bksd.word_ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bksd.core_ui.theme.AppTheme
import com.bksd.word_ui.model.WordDetailUi
import com.bksd.word_ui.model.WordOfDayDetailCardUi

/**
 * WordDetailCard: shows the word of the day with pronunciation and a snippet, clickable.
 */
@Composable
fun WordDetailCard(
    detailUi: WordDetailUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            if (detailUi is WordOfDayDetailCardUi) {
                Text(
                    text = detailUi.label,
                    style = AppTheme.typography.heading2,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            detailUi.word?.let {
                Spacer(modifier.height(8.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
            }
            detailUi.definition?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2
                )
            }

            Spacer(modifier.height(8.dp))
            detailUi.synonyms?.firstOrNull()?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2
                )
            }

            detailUi.antonyms?.firstOrNull()?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2
                )
            }
        }
    }
}