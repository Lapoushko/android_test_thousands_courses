package com.lapoushko.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lapoushko.feature.model.CourseItem

/**
 * @author Lapoushko
 */
class MainScreenViewModel : ViewModel() {
    private var _state = MutableMainScreenState()
    val state = _state as MainScreenState


    private class MutableMainScreenState : MainScreenState {
        override var initialCourses: List<CourseItem> by mutableStateOf(
            listOf(
                CourseItem(
                    id = 123,
                    title = "Java-разработчик с нуля",
                    text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
                    price = "999 Р",
                    rate = "4.9",
                    startDate = "2024-05-22",
                    hasLike = false,
                    publishDate = "2024-02-02",
                    image = ""
                )
            )
        )
        override val isSortByNew: Boolean by mutableStateOf(false)
    }
}

interface MainScreenState {
    val initialCourses: List<CourseItem>
    val isSortByNew: Boolean
}