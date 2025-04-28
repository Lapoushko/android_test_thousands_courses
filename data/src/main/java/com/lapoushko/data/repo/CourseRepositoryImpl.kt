package com.lapoushko.data.repo

import com.lapoushko.domain.source.CourseDataSource
import com.lapoushko.domain.entity.Course
import com.lapoushko.domain.repo.CourseRepository
import com.lapoushko.domain.service.CourseService
import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 */
class CourseRepositoryImpl(
    private val courseService: CourseService,
    private val courseDataSource: CourseDataSource
): CourseRepository {
    override fun getCourses(): Flow<List<Course>> {
        return courseService.getCourses()
    }

    override fun getFavoritesCourses(): Flow<List<Course>> {
        return courseDataSource.getCourses()
    }

    override suspend fun saveCourse(course: Course) {
        courseDataSource.saveCourse(course)
    }

    override suspend fun deleteCourse(id: Long) {
        courseDataSource.deleteCourse(id)
    }
}