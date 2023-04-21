package org.android.go.sopt.data.repository

import org.android.go.sopt.data.source.RepoDataSource
import org.android.go.sopt.domain.model.Repo
import org.android.go.sopt.domain.repository.RepoRepository
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoDataSource: RepoDataSource,
) : RepoRepository {
    override suspend fun getRepoList(): Result<List<Repo>> = runCatching {
        repoDataSource.getRepoList().map { repo -> repo.toRepo() }
    }
}
