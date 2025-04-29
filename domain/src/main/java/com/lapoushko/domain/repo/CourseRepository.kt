package com.lapoushko.domain.repo

import com.lapoushko.domain.entity.Course
import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 */
interface CourseRepository {
    fun getCourses(): Flow<List<Course>>

    fun getFavoritesCourses(): Flow<List<Course>>

    suspend fun saveCourse(course: Course)

    suspend fun deleteCourse(id: Long)
}