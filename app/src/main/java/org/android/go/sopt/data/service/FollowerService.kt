package org.android.go.sopt.data.service

import org.android.go.sopt.data.entity.remote.response.ResponseGetFollowerListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("users")
    fun getFollowerList(
        @Query("page") page: Int,
    ): ResponseGetFollowerListDto
}
