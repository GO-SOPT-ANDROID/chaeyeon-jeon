package org.android.go.sopt.data.source

import org.android.go.sopt.data.entity.MockRepoDto
import org.android.go.sopt.data.service.RepoService
import javax.inject.Inject

class RepoDataSource @Inject constructor(
    private val repoService: RepoService,
) {
    suspend fun getRepoList(): MockRepoDto = repoService.getRepoList()
}
