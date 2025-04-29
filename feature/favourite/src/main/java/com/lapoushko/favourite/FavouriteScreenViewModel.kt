package com.lapoushko.favourite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lapoushko.domain.repo.CourseRepository
import com.lapoushko.feature.mapper.CourseMapper
import com.lapoushko.feature.model.CourseItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * @author Lapoushko
 */
class FavouriteScreenViewModel(
    private val repository: CourseRepository,
    private val mapper: CourseMapper
) : ViewModel() {
    private var _state = MutableFavouriteScreenState()
    val state = _state as FavouriteScreenState

    init {
        loadCourses()
    }

    private fun loadCourses() {
        repository.getFavoritesCourses().onEach { courses ->
            _state.initialCourses = courses.map { mapper.toUi(it) }
        }.launchIn(viewModelScope)
    }

    fun deleteCourse(courseItem: CourseItem) {
        viewModelScope.launch {
            repository.deleteCourse(courseItem.id)
        }
    }

    private class MutableFavouriteScreenState : FavouriteScreenState {
        override var initialCourses: List<CourseItem> by mutableStateOf(emptyList())
    }
}

interface FavouriteScreenState {
    val initialCourses: List<CourseItem>
}