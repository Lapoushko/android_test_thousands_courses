package com.lapoushko.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.lapoushko.feature.CourseItem
import com.lapoushko.ui.theme.DarkGray
import com.lapoushko.ui.theme.Green
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

}

@Composable
private fun SearchBar(){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {  }
}

@Composable
private fun CourseItemCard(course: CourseItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(236.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkGray
        )
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Box {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(114.dp),
                    model = course.image.ifEmpty { "https://masterpiecer-images.s3.yandex.net/5fd531dca6427c7:upscaled" },
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }
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
                        color = PlaceholderInputColor
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = course.price, style = Typography.titleMedium, color = White)
                        Row {
                            Text(text = "Подробнее ", style = Typography.bodySmall, color = Green)
                            Icon(
                                painter = painterResource(R.drawable.arrow),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CourseItemCardPreview() {
    CourseItemCard(
        course = CourseItem(
            id = "100",
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