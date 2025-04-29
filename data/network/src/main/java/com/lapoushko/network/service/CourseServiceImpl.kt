package com.lapoushko.network.service

import android.content.Context
import com.lapoushko.domain.entity.Course
import com.lapoushko.domain.service.CourseService
import com.lapoushko.network.entity.CourseResponse
import com.lapoushko.network.mapper.CourseResponseMapper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import java.io.File
import java.nio.charset.Charset

/**
 * @author Lapoushko
 */
class CourseServiceImpl(
    private val courseService: RetrofitCourseService,
    private val mapper: CourseResponseMapper,
    private val context: Context
) : CourseService {
    override fun getCourses(): Flow<List<Course>> = flow {
        downloadAndSaveJson()
        val response = parseJsonFile()
        val result = mutableListOf<Course>()

        if (response?.courses != null) {
            response.courses.map { course: com.lapoushko.network.entity.Course? ->
                if (course != null) {
                    result.add(mapper.toDomain(course))
                }
            }
        }
        emit(result)
    }

    private suspend fun downloadAndSaveJson(): File? {
        return withContext(Dispatchers.IO) {
            try {
                val response = courseService.downloadFile()
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        saveJsonToFile(body)
                    }
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun saveJsonToFile(body: ResponseBody): File? {
        return try {
            val file = File(context.getExternalFilesDir(null), FILE_NAME)
            body.byteStream().use { inputStream ->
                file.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun loadJSONFromFile(): String? {
        return try {
            val file = File(context.getExternalFilesDir(null), FILE_NAME)
            file.readText(Charset.forName("UTF-8"))
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun parseJsonFile(): CourseResponse? {
        return try {
            val json = loadJSONFromFile()
            if (json != null) {
                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                val adapter = moshi.adapter(CourseResponse::class.java)
                adapter.fromJson(json)
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        private const val FILE_NAME = "mockdata.json"
    }

}