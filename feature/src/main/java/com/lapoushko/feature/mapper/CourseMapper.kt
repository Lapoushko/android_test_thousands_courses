package com.lapoushko.feature.mapper

import com.lapoushko.domain.entity.Course
import com.lapoushko.feature.model.CourseItem

/**
 * @author Lapoushko
 */
interface CourseMapper{
    fun toUi(course: Course): CourseItem

    fun toDomain(course: CourseItem): Course
}

class CourseMapperImpl(): CourseMapper{
    override fun toUi(course: Course): CourseItem {
        course.apply {
            return CourseItem(
                id = id,
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

    override fun toDomain(course: CourseItem): Course {
        course.apply {
            return Course(
                id = id,
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