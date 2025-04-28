package com.lapoushko.android_test_thousands_courses.di

import com.lapoushko.feature.mapper.CourseMapper
import com.lapoushko.feature.mapper.CourseMapperImpl
import com.lapoushko.network.mapper.CourseResponseMapper
import com.lapoushko.network.mapper.CourseResponseMapperImpl
import org.koin.dsl.module

/**
 * @author Lapoushko
 */
val mapperModule = module {
    factory<CourseMapper> { CourseMapperImpl() }
    factory<CourseResponseMapper> { CourseResponseMapperImpl() }
}