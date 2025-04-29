package com.lapoushko.storage.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lapoushko.storage.entity.CourseDb
import com.lapoushko.storage.util.ConstantsDatabase

/**
 * @author Lapoushko
 */
@Database(
    entities = [
        CourseDb::class
    ],
    version = ConstantsDatabase.VERSION_DATABASE
)
abstract class CourseDatabase: RoomDatabase(){
    abstract fun CourseDao(): CourseDao
}