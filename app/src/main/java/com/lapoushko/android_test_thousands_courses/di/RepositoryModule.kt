package com.lapoushko.android_test_thousands_courses.di

import com.lapoushko.data.repo.CourseRepositoryImpl
import com.lapoushko.domain.repo.CourseRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * @author Lapoushko
 */
val repositoryModule = module {
    singleOf(::CourseRepositoryImpl){ bind<CourseRepository>() }
}