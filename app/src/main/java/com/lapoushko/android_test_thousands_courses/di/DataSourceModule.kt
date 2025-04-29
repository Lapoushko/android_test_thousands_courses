package com.lapoushko.android_test_thousands_courses.di

import android.content.Context
import androidx.room.Room
import com.lapoushko.domain.source.CourseDataSource
import com.lapoushko.storage.dao.CourseDao
import com.lapoushko.storage.dao.CourseDatabase
import com.lapoushko.storage.source.CourseDataSourceImpl
import com.lapoushko.storage.util.ConstantsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @author Lapoushko
 */
val dataSourceModule = module {
    single<CourseDataSource> { CourseDataSourceImpl(get(), get()) }
    single<CourseDatabase> { provideRoomDatabase(androidContext()) }
    single<CourseDao> { provideDao(get()) }
}

private fun provideRoomDatabase(context: Context): CourseDatabase {
    return Room.databaseBuilder(context, CourseDatabase::class.java, ConstantsDatabase.NAME_DATABASE)
        .build()
}

private fun provideDao(database: CourseDatabase): CourseDao {
    return database.CourseDao()
}