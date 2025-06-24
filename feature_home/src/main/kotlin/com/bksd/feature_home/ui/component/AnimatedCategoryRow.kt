package com.bksd.feature_home.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bksd.core_ui.theme.AppTheme

/**
 * AnimatedCategoryRow: displays a row of categories with a sliding highlight.
 * @param categories labels for each category.
 * @param icons corresponding icons for each category.
 * @param selectedIndex currently selected index.
 * @param onSelect lambda invoked when a category is selected.
 */
@Composable
fun AnimatedCategoryRow(
    categories: List<String> = listOf("Synonyms", "Antonyms", "Examples"),
    icons: List<ImageVector> = listOf(
        Icons.Default.Build,
        Icons.Default.Share,
        Icons.Default.PlayArrow
    ),
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    // track widths of individual items
    val itemWidths = remember { mutableStateListOf<Dp>() }
    val density = LocalDensity.current

    // calculate offset based on widths
    val offsetX by animateDpAsState(
        targetValue = itemWidths
            .take(selectedIndex)
            .sumOf { it.value.toDouble() }
            .dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )
    // width of highlight
    val capsuleWidth by animateDpAsState(
        targetValue = itemWidths.getOrNull(selectedIndex) ?: 0.dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    Box(
        modifier = Modifier
            .height(40.dp)
            .padding(horizontal = 16.dp)
    ) {
        // highlight capsule
        Box(
            modifier = Modifier
                .offset(x = offsetX)
                .width(capsuleWidth)
                .fillMaxHeight()
                .background(
                    color = AppTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(20.dp)
                )
        )

        Row(modifier = Modifier.fillMaxSize()) {
            categories.forEachIndexed { index, label ->
                var widthPx by remember { mutableIntStateOf(0) }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .onGloballyPositioned { coords ->
                            widthPx = coords.size.width
                            val wDp = with(density) { widthPx.toDp() }
                            if (itemWidths.size <= index) itemWidths.add(wDp)
                            else itemWidths[index] = wDp
                        }
                        .clickable { onSelect(index) }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    val isSelected = index == selectedIndex
                    val contentColor by animateColorAsState(
                        targetValue = if (isSelected)
                            MaterialTheme.colorScheme.onSecondaryContainer
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        animationSpec = tween(300)
                    )
                    Icon(
                        imageVector = icons[index],
                        contentDescription = null,
                        tint = contentColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = label,
                        color = contentColor,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}