package com.lapoushko.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lapoushko.domain.entity.Course
import com.lapoushko.domain.repo.CourseRepository
import com.lapoushko.extension.toDate
import com.lapoushko.feature.mapper.CourseMapper
import com.lapoushko.feature.model.CourseItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * @author Lapoushko
 */
class MainScreenViewModel(
    private val repository: CourseRepository,
    private val mapper: CourseMapper
) : ViewModel() {
    private var _state = MutableMainScreenState()
    val state = _state as MainScreenState

    init {
        loadCourses()
    }

    private fun loadCourses(){
        repository.getCourses().onEach { courses: List<Course> ->
            _state.initialCourses = courses.map { course -> mapper.toUi(course) }
            _state.statusLoading = MainScreenState.StatusLoading.SUCCESS
        }.launchIn(viewModelScope)
    }

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

    fun saveOrDeleteCourse(courseItem: CourseItem){
        viewModelScope.launch {
            if (!courseItem.hasLike) {
                repository.saveCourse(mapper.toDomain(courseItem.copy(hasLike = true)))
            } else {
                repository.deleteCourse(courseItem.id)
            }
        }
    }

    private class MutableMainScreenState : MainScreenState {
        override var initialCourses: List<CourseItem> by mutableStateOf(emptyList())
        override var isSortByDescending: Boolean by mutableStateOf(true)
        override var statusLoading: MainScreenState.StatusLoading by mutableStateOf(MainScreenState.StatusLoading.LOADING)
    }
}

interface MainScreenState {
    val initialCourses: List<CourseItem>
    val isSortByDescending: Boolean
    val statusLoading: StatusLoading
    enum class StatusLoading{
        LOADING,
        SUCCESS
    }
}