package com.lapoushko.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lapoushko.storage.util.ConstantsDatabase

/**
 * @author Lapoushko
 */
@Entity(tableName = ConstantsDatabase.NAME_TABLE_GROUPS)
data class CourseDb(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val idLong : Long?,
    val title: String?,
    val text: String?,
    val price: String?,
    val rate: String?,
    val startDate: String?,
    val hasLike: Boolean?,
    val publishDate: String?,
    val image: String?
)