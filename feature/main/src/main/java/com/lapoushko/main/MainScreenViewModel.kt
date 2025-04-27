package com.lapoushko.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lapoushko.extension.toDate
import com.lapoushko.feature.model.CourseItem

/**
 * @author Lapoushko
 */
class MainScreenViewModel : ViewModel() {
    private var _state = MutableMainScreenState()
    val state = _state as MainScreenState

    fun sort(){
        if (state.isSortByDescending){
            _state.initialCourses = _state.initialCourses.sortedByDescending {
                _state.isSortByDescending = false
                it.publishDate.toDate()
            }
        }
        else{
            _state.initialCourses = _state.initialCourses.sortedBy {
                _state.isSortByDescending = true
                it.publishDate.toDate()
            }
        }
    }

    private class MutableMainScreenState : MainScreenState {
        override var initialCourses: List<CourseItem> by mutableStateOf(
            listOf(
                CourseItem(
                    id = 123,
                    title = "Java-разработчик с нуля",
                    text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
                    price = "999",
                    rate = "4.9",
                    startDate = "2024-05-22",
                    hasLike = false,
                    publishDate = "2024-05-22",
                    image = ""
                ),
                CourseItem(
                    id = 123,
                    title = "Java-разработчик с нуля",
                    text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
                    price = "999",
                    rate = "4.9",
                    startDate = "2024-06-22",
                    hasLike = false,
                    publishDate = "2024-06-22",
                    image = ""
                )

            )
        )
        override var isSortByDescending: Boolean by mutableStateOf(false)
    }
}

interface MainScreenState {
    val initialCourses: List<CourseItem>
    val isSortByDescending: Boolean
}