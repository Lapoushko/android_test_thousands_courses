package com.lapoushko.storage.source

import android.util.Log
import com.lapoushko.domain.entity.Course
import com.lapoushko.domain.source.CourseDataSource
import com.lapoushko.storage.dao.CourseDao
import com.lapoushko.storage.mapper.CourseStorageMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * @author Lapoushko
 */
class CourseDataSourceImpl(
    private val dao: CourseDao,
    private val mapper: CourseStorageMapper
) : CourseDataSource {
    override fun getCourses(): Flow<List<Course>> {
        return dao.getCourses().map { courses ->
            courses?.map { mapper.toDomain(it) } ?: emptyList()
        }
    }

    override suspend fun getCourse(id: Long): Course? {
        return withContext(Dispatchers.IO) {
            val course = dao.getCourse(id)
            course?.let {
                mapper.toDomain(it)
            }
        }
    }

    override suspend fun saveCourse(course: Course) {
        dao.insertCourse(mapper.toDb(course))
    }

    override suspend fun deleteCourse(id: Long) {
        dao.deleteCourse(id)
    }

}