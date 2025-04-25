package com.lapoushko.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lapoushko.ui.NextButton
import com.lapoushko.ui.Title
import com.lapoushko.ui.theme.Green
import com.lapoushko.ui.theme.LightGray
import com.lapoushko.ui.theme.Typography
import com.lapoushko.ui.theme.White
import com.lapoushko.ui.theme.horizontalPadding
import com.lapoushko.ui.theme.verticalArrangement
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

/**
 * @author Lapoushko
 */
@Composable
fun OnboardingScreen(
    viewModel: OnboardingScreenViewModel = koinViewModel(),
    onClick: () -> Unit
) {
    val state = viewModel.state

    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        Column(verticalArrangement = Arrangement.spacedBy(verticalArrangement)) {
            Title(textAlign = TextAlign.Center, text = "Тысячи курсов в одном месте", modifier = Modifier.padding(horizontal = horizontalPadding))
            LazyListItems(state.items)
        }
        NextButton(onClick = onClick, title = "Продолжить", modifier = Modifier.padding(vertical = 32.dp, horizontal = horizontalPadding))
    }
}

@Composable
private fun LazyListItems(list: List<String>) {
    val density = LocalDensity.current
    val config = LocalConfiguration.current
    val displayMetrics = LocalContext.current.resources.displayMetrics

    // Границы ширины строки
    val screenWidthPx = displayMetrics.widthPixels
    val maxLineWidthPx = screenWidthPx + with(density) { 10.dp.toPx() }

    // Состояние активных кнопок
    val activeStates = remember { mutableStateListOf<Boolean>().apply { repeat(list.size) { add(false) } } }

    // Скролл и ширина контента
    val scrollState = rememberScrollState()
    val contentWidthPx = remember { mutableIntStateOf(0) }

    // Центрирование после измерения ширины
    LaunchedEffect(contentWidthPx.intValue) {
        if (contentWidthPx.intValue > 0) {
            with(density) {
                val screenWidth = config.screenWidthDp.dp.toPx()
                val scrollTo = contentWidthPx.intValue / 2 - (screenWidth / 2).toInt()
                scrollState.scrollTo(scrollTo.coerceAtLeast(0))
            }
        }
    }

    // Оценка ширины каждой кнопки
    val itemWidths = remember(list) {
        list.map { text ->
            with(density) {
                val approxTextWidth = text.length * 24
                val padding = 40
                val spacing = 4
                (approxTextWidth + padding + spacing).toDp()
            }
        }
    }

    // Группировка кнопок в ряды с ограничением по ширине
    val buttonRows = remember(itemWidths) {
        val rows = mutableListOf<MutableList<Int>>()
        var currentRow = mutableListOf<Int>()
        var currentRowWidth = 0

        itemWidths.forEachIndexed { index, widthDp ->
            val widthPx = with(density) { widthDp.toPx() }.toInt()

            if (currentRowWidth + widthPx > maxLineWidthPx) {
                rows.add(currentRow)
                currentRow = mutableListOf(index)
                currentRowWidth = widthPx
            } else {
                currentRow.add(index)
                currentRowWidth += widthPx
            }
        }
        if (currentRow.isNotEmpty()) rows.add(currentRow)
        rows
    }

    // Отображение с горизонтальным скроллом и центрированием контента
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .onGloballyPositioned { coordinates ->
                contentWidthPx.intValue = coordinates.size.width
            },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            buttonRows.forEach { row ->
                Row(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .wrapContentWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    row.forEach { index ->
                        ItemButton(
                            text = list[index],
                            isActive = activeStates[index],
                            onClick = { isActive -> activeStates[index] = isActive }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemButton(text: String, onClick: (Boolean) -> Unit, isActive: Boolean) {
    var rotation by remember { mutableFloatStateOf(0f) }
    val rotValue = 15f

    val animatedRotation: Float by animateFloatAsState(targetValue = rotation, label = "")

    Button(
        onClick = {
            rotation = if (rotation == 0f) {
                if (Random.nextBoolean()) rotValue else -rotValue
            } else {
                0f
            }
            onClick(!isActive)
        },
        colors = ButtonDefaults.buttonColors(containerColor = if (isActive) Green else LightGray, contentColor = White),
        modifier = Modifier.graphicsLayer(rotationZ = animatedRotation)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            text = text,
            style = Typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    OnboardingScreen(onClick = {})
}