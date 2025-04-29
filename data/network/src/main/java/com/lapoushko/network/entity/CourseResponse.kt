package com.lapoushko.network.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseResponse(
    @Json(name = "courses")
    val courses: List<Course?>? = null
)