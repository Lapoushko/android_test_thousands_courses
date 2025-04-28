package com.lapoushko.feature.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.lapoushko.feature.R
import com.lapoushko.feature.model.CourseItem
import com.lapoushko.ui.theme.DarkGray
import com.lapoushko.ui.theme.PlaceholderInputColor
import com.lapoushko.ui.theme.Typography
import com.lapoushko.ui.theme.horizontalPadding

/**
 * @author Lapoushko
 */
/**
 * @author Lapoushko
 */
@Composable
fun CourseItemCard(
    course: CourseItem,
    onFavourite: () -> Unit,
    onToDetail: (Long) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(236.dp)
            .clickable { onToDetail(course.id) },
        colors = CardDefaults.cardColors(
            containerColor = DarkGray
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ImageCard(course = course, onFavourite = { onFavourite() })
            TextCard(course = course)
        }
    }
}

@Composable
private fun ImageCard(
    course: CourseItem,
    onFavourite: () -> Unit
) {
    var isFavourite by remember { mutableStateOf(course.hasLike) }
    Box {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(114.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = course.image,
            contentScale = ContentScale.Crop,
            contentDescription = "",
        )

        IconButton(
            onClick = {
                onFavourite()
                isFavourite = !isFavourite
            },
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(DarkGray.copy(alpha = 0.4f))
                .align(Alignment.TopEnd),
        ) {
            Icon(
                painter = if (isFavourite) painterResource(R.drawable.added_favourite) else painterResource(
                    R.drawable.favourite
                ),
                contentDescription = null,
                tint = if (isFavourite) Green else White,
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
                Text(text = course.publishDate, style = Typography.bodySmall, color = White)
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
                Text(text = "${course.price} ₽", style = Typography.titleMedium, color = White)
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
                DarkGray.copy(alpha = 0.4f)
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
            publishDate = "2024-05-22",
            image = ""
        ), onToDetail = {},
        onFavourite = {}
    )
}
