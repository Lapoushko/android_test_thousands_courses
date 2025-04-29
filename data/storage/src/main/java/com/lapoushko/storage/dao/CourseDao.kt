package com.lapoushko.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lapoushko.storage.entity.CourseDb
import kotlinx.coroutines.flow.Flow

/**
 * @author Lapoushko
 */
@Dao
interface CourseDao{
    @Query("SELECT * FROM course")
    fun getCourses() : Flow<List<CourseDb>?>

    @Query("SELECT * FROM course WHERE idLong = :id")
    suspend fun getCourse(id: Long) : CourseDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(courseDb: CourseDb)

    @Query("DELETE FROM course WHERE idLong = :id")
    suspend fun deleteCourse(id: Long)
}