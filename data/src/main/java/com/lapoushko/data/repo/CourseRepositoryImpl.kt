package com.lapoushko.data.repo

import com.lapoushko.domain.entity.Course
import com.lapoushko.domain.repo.CourseRepository
import com.lapoushko.domain.service.CourseService
import com.lapoushko.domain.source.CourseDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Lapoushko
 */
class CourseRepositoryImpl(
    private val courseService: CourseService,
    private val courseDataSource: CourseDataSource
) : CourseRepository {
    override fun getCourses(): Flow<List<Course>> = flow {
        courseService.getCourses().collect { courses ->
            val newCourses = mutableListOf<Course>()
            courses.forEach { course ->
                if (course.hasLike) {
                    courseDataSource.saveCourse(course)
                }
                if (courseDataSource.getCourse(course.id) != null) {
                    newCourses.add(course.copy(hasLike = true))
                } else newCourses.add(course)
            }
            emit(newCourses)
        }
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