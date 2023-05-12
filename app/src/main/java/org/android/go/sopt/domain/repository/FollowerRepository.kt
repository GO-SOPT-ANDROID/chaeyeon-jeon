package org.android.go.sopt.domain.repository

import org.android.go.sopt.domain.model.Follower

interface FollowerRepository {
    suspend fun getFollowerList(page: Int): Result<List<Follower>>
}
