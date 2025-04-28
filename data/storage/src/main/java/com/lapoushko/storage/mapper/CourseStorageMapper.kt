package com.lapoushko.storage.mapper

import com.lapoushko.domain.entity.Course
import com.lapoushko.storage.entity.CourseDb

/**
 * @author Lapoushko
 */
interface CourseStorageMapper{
    fun toDomain(course: CourseDb): Course

    fun toDb(course: Course): CourseDb
}

class CourseStorageMapperImpl(): CourseStorageMapper{
    override fun toDomain(course: CourseDb): Course {
        course.apply {
            return Course(
                id = idLong ?: 0,
                title = title ?: "",
                text = text ?: "",
                price = price ?: "",
                rate = rate ?: "",
                startDate = startDate ?: "",
                hasLike = hasLike ?: false,
                publishDate = publishDate ?: "",
                image = image ?: ""
            )
        }
    }

    override fun toDb(course: Course): CourseDb {
        course.apply {
            return CourseDb(
                idLong = id,
                title = title,
                text = text,
                price = price,
                rate = rate,
                startDate = startDate,
                hasLike = hasLike,
                publishDate = publishDate,
                image = image
            )
        }
    }
}