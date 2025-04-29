package com.lapoushko.domain.service

import com.lapoushko.domain.entity.Course
import kotlinx.coroutines.flow.Flow

/**
* @author Lapoushko
*/
interface CourseService {
    fun getCourses(): Flow<List<Course>>
}