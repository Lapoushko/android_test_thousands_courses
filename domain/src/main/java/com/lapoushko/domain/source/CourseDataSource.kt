package com.lapoushko.domain.source

import com.lapoushko.domain.entity.Course
import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 */
interface CourseDataSource {
    fun getCourses(): Flow<List<Course>>

    suspend fun getCourse(id: Long): Course?

    suspend fun saveCourse(course: Course)

    suspend fun deleteCourse(id: Long)
}