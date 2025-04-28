package com.lapoushko.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lapoushko.extension.toCustomString
import com.lapoushko.extension.toDate
import com.lapoushko.feature.screen.CourseItemCard
import com.lapoushko.ui.theme.Green
import com.lapoushko.ui.theme.LightGray
import com.lapoushko.ui.theme.PlaceholderInputColor
import com.lapoushko.ui.theme.Typography
import com.lapoushko.ui.theme.White
import com.lapoushko.ui.theme.horizontalPadding
import org.koin.androidx.compose.koinViewModel

/**
 * @author Lapoushko
 */
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = koinViewModel(),
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
        CustomSearchBar()
        SortButton(onSort = { viewModel.sort() })
        when(state.statusLoading){
            MainScreenState.StatusLoading.LOADING -> CircularProgressIndicator(color = White)
            MainScreenState.StatusLoading.SUCCESS -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.initialCourses) { course ->
                        CourseItemCard(
                            course.copy(
                                publishDate = course.publishDate.toDate().toCustomString()
                            )
                        ) {
                            onToDetail(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CustomSearchBar() {
    val height = 56.dp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .weight(0.5f),
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = "Search courses...",
                    style = Typography.bodyMedium,
                    color = PlaceholderInputColor
                )
            },
            leadingIcon = {
                IconButton(onClick = {}, modifier = Modifier.size(48.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.search_icon),
                        contentDescription = null,
                        tint = White
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGray,
                unfocusedContainerColor = LightGray,
                disabledContainerColor = LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(28.dp),
        )

        FilterButton(height)
    }

}

@Composable
private fun FilterButton(height: Dp) {
    IconButton(
        onClick = {},
        modifier = Modifier
            .size(height)
            .clip(CircleShape),
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = White,
            containerColor = LightGray
        ),
    ) {
        Icon(
            painter = painterResource(R.drawable.filter_icon),
            contentDescription = null,
            tint = White
        )
    }
}

@Composable
private fun SortButton(onSort: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.clickable { onSort() }) {
            Text(
                text = "По дате добавления",
                style = Typography.labelLarge,
                color = Green
            )
            Icon(
                painter = painterResource(R.drawable.sort),
                contentDescription = null,
                tint = Green
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen(onToDetail = {})
}