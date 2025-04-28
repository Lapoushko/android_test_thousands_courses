package com.lapoushko.storage.source

import com.lapoushko.domain.entity.Course
import com.lapoushko.domain.source.CourseDataSource
import com.lapoushko.storage.dao.CourseDao
import com.lapoushko.storage.mapper.CourseStorageMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

/**
 * @author Lapoushko
 */
class CourseDataSourceImpl(
    private val dao: CourseDao,
    private val mapper: CourseStorageMapper
) : CourseDataSource {
    override fun getCourses(): Flow<List<Course>> {
        return dao.getCourses()?.map { courses -> courses.map { mapper.toDomain(it) } }
            ?: flowOf(emptyList())
    }

    override fun getCourse(id: Long): Flow<Course> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCourse(course: Course) {
        dao.insertCourse(mapper.toDb(course))
    }

    override suspend fun deleteCourse(id: Long) {
        dao.deleteCourse(id)
    }

}