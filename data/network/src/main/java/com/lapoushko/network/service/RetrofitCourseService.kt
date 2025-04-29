package com.lapoushko.network.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming

/**
 * @author Lapoushko
 */
interface RetrofitCourseService {
    @Streaming
    @GET("/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun downloadFile(): Response<ResponseBody>
}