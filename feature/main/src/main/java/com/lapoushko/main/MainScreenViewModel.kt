package com.lapoushko.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lapoushko.feature.CourseItem

/**
 * @author Lapoushko
 */
class MainScreenViewModel: ViewModel() {
    private var _state = MutableMainScreenState()
    val state = _state as MainScreenState


    private class MutableMainScreenState: MainScreenState{
        override var initialCourses: List<CourseItem> by mutableStateOf(emptyList())
        override val isSortByNew: Boolean by mutableStateOf(false)
    }
}

interface MainScreenState{
    val initialCourses: List<CourseItem>
    val isSortByNew: Boolean
}