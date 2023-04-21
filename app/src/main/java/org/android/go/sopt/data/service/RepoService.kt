package org.android.go.sopt.data.service

import org.android.go.sopt.data.entity.MockRepoDto
import retrofit2.http.GET

interface RepoService {
    @GET("fake_repo_list.json")
    suspend fun getRepoList(): MockRepoDto
}
