package org.android.go.sopt.domain.repository

import org.android.go.sopt.domain.model.Repo

interface RepoRepository {
    suspend fun getRepoList(): Result<List<Repo>>
}
