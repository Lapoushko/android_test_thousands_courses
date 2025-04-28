package com.lapoushko.data.repo

import com.lapoushko.domain.entity.Course
import com.lapoushko.domain.repo.CourseRepository
import com.lapoushko.domain.service.CourseService
import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 */
class CourseRepositoryImpl(
    private val courseService: CourseService
): CourseRepository {
    override fun getCourses(): Flow<List<Course>> {
        return courseService.getCourses()
    }

    override fun getFavoritesCourses(): Flow<List<Course>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCourse(course: Course) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCourse(id: Long) {
        TODO("Not yet implemented")
    }
}