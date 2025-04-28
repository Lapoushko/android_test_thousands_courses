package com.lapoushko.network.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Course(
    @Json(name = "hasLike")
    val hasLike: Boolean? = null,
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "price")
    val price: String? = null,
    @Json(name = "publishDate")
    val publishDate: String? = null,
    @Json(name = "rate")
    val rate: String? = null,
    @Json(name = "startDate")
    val startDate: String? = null,
    @Json(name = "text")
    val text: String? = null,
    @Json(name = "title")
    val title: String? = null
)