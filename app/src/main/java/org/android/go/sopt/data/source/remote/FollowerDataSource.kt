package org.android.go.sopt.data.source.remote

import org.android.go.sopt.data.entity.remote.response.ResponseGetFollowerListDto
import org.android.go.sopt.data.service.FollowerService
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val followerService: FollowerService,
) {
    fun getFollowerList(page: Int): ResponseGetFollowerListDto =
        followerService.getFollowerList(page)
}
