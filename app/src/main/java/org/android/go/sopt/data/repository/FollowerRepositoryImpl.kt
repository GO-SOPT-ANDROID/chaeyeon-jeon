package org.android.go.sopt.data.repository

import org.android.go.sopt.data.source.remote.FollowerDataSource
import org.android.go.sopt.domain.model.Follower
import org.android.go.sopt.domain.repository.FollowerRepository
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource,
) : FollowerRepository {
    override suspend fun getFollowerList(page: Int): Result<List<Follower>> =
        runCatching {
            followerDataSource.getFollowerList(page).toFollower()
        }
}
