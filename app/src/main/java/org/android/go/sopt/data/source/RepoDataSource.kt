package org.android.go.sopt.data.source

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.android.go.sopt.data.entity.MockRepoDto
import org.android.go.sopt.util.AssetLoader
import javax.inject.Inject

class RepoDataSource @Inject constructor(
    private val assetLoader: AssetLoader,
) {
    fun getRepoList(): Array<MockRepoDto> {
        return assetLoader.getJsonString("fake_repo_list.json")?.let { jsonString ->
            Json.decodeFromString<Array<MockRepoDto>>(jsonString)
        } ?: emptyArray()
    }
}
