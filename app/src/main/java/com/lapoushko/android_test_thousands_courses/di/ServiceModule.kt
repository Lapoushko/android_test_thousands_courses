package com.lapoushko.android_test_thousands_courses.di

import com.lapoushko.domain.service.CourseService
import com.lapoushko.network.service.CourseServiceImpl
import com.lapoushko.network.service.RetrofitCourseService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author Lapoushko
 */
val serviceModule = module {
    single<CourseService> { CourseServiceImpl(get(), get(), androidContext()) }
    single<Retrofit>{ provideRetrofit() }
    single<RetrofitCourseService> { provideApiService(get()) }
}

fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

fun provideApiService(retrofit: Retrofit): RetrofitCourseService = retrofit.create(RetrofitCourseService::class.java)

private const val BASE_URL = "https://drive.usercontent.google.com"