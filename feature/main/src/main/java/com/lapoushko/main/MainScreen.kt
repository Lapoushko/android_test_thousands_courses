package com.lapoushko.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.lapoushko.feature.CourseItem
import com.lapoushko.ui.theme.DarkGray
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
    viewModel: MainScreenViewModel = koinViewModel()
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
        SortButton(onSort = {})
        LazyColumn {
            items(state.initialCourses) { course ->
                CourseItemCard(course)
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
            shape = RoundedCornerShape(28.dp)
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

@Composable
private fun CourseItemCard(course: CourseItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(236.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkGray
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ImageCard(course = course, onFavourite = {})
            TextCard(course = course)
        }
    }
}

@Composable
private fun ImageCard(
    course: CourseItem,
    onFavourite: () -> Unit
) {
    Box {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(114.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = course.image.ifEmpty { "https://masterpiecer-images.s3.yandex.net/5fd531dca6427c7:upscaled" },
            contentScale = ContentScale.Crop,
            contentDescription = "",
        )

        IconButton(
            onClick = { onFavourite() },
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(LightGray.copy(alpha = 0.4f))
                .align(Alignment.TopEnd),
        ) {
            Icon(
                painter = painterResource(R.drawable.favourite),
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(20.dp)
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ChipBlurContent(
                content = {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(
                            modifier = Modifier.size(12.dp),
                            painter = painterResource(R.drawable.star),
                            contentDescription = null,
                            tint = Green
                        )
                        Text(
                            text = course.rate,
                            style = Typography.bodySmall,
                            color = White
                        )
                    }
                }
            )
            ChipBlurContent(content = {
                Text(text = course.startDate, style = Typography.bodySmall, color = White)
            }
            )
        }
    }
}

@Composable
private fun TextCard(course: CourseItem) {
    Column(
        modifier = Modifier.padding(horizontal = horizontalPadding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(text = course.title, style = Typography.titleMedium)
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = course.text,
                style = Typography.bodySmall,
                color = PlaceholderInputColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = course.price, style = Typography.titleMedium, color = White)
                Row {
                    Text(
                        text = "Подробнее ",
                        style = Typography.bodySmall,
                        color = Green
                    )
                    Icon(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = null,
                        tint = Green
                    )
                }
            }
        }
    }
}

@Composable
private fun ChipBlurContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    shape: Shape = RoundedCornerShape(12.dp)
) {
    Box(
        modifier = modifier
            .padding(horizontal = 6.dp, vertical = 4.dp)
            .height(22.dp)
            .clip(shape)
            .background(
                LightGray.copy(alpha = 0.4f)
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
private fun CircleBlurContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(6.dp)
            .height(28.dp)
            .clip(CircleShape)
            .background(
                LightGray.copy(alpha = 0.4f)
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CourseItemCardPreview() {
    CourseItemCard(
        course = CourseItem(
            id = 100,
            title = "Java-разработчик с нуля",
            text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
            price = "999",
            rate = "4.9",
            startDate = "2024-05-22",
            hasLike = false,
            publishDate = "2024-02-02",
            image = ""
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}