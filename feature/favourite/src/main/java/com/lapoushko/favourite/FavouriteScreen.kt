package com.lapoushko.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lapoushko.extension.toCustomString
import com.lapoushko.extension.toDate
import com.lapoushko.feature.screen.CourseItemCard
import com.lapoushko.ui.theme.Typography
import com.lapoushko.ui.theme.horizontalPadding
import org.koin.androidx.compose.koinViewModel

/**
 * @author Lapoushko
 */
@Composable
fun FavouriteScreen(
    viewModel: FavouriteScreenViewModel = koinViewModel(),
    onToDetail: (Long) -> Unit
) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = horizontalPadding)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Избранное",
            textAlign = TextAlign.Start, style = Typography.titleSmall,
        )

        LazyColumn {
            items(state.initialCourses) { course ->
                CourseItemCard(course.copy(
                    publishDate = course.publishDate.toDate().toCustomString()
                )) {
                    onToDetail(it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavouriteScreenPreview() {
    FavouriteScreen(onToDetail = {})
}