package com.lapoushko.domain.entity

/**
 * @author Lapoushko
 */
class Course(
    val id : Long,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
    val image: String
)