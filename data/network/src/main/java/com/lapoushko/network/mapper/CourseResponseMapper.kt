package com.lapoushko.network.mapper

import com.lapoushko.network.entity.Course

/**
 * @author Lapoushko
 */
interface CourseResponseMapper{
    fun toDomain(course: Course): com.lapoushko.domain.entity.Course
}

class CourseResponseMapperImpl(): CourseResponseMapper{
    override fun toDomain(course: Course): com.lapoushko.domain.entity.Course {
        course.apply {
            return com.lapoushko.domain.entity.Course(
                id = id ?: 0,
                title = title ?: "",
                text = text ?: "",
                price = price ?: "",
                rate = rate ?: "",
                startDate = startDate ?: "",
                hasLike = hasLike ?: false,
                publishDate = publishDate ?: "",
                image = "https://masterpiecer-images.s3.yandex.net/5fd531dca6427c7:upscaled"
            )
        }
    }

}