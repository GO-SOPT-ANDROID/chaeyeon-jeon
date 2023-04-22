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
        return assetLoader.getJsonString(FILE_FAKE_REPO_LIST)?.let { jsonString ->
            Json.decodeFromString<Array<MockRepoDto>>(jsonString)
        } ?: emptyArray()
    }

    companion object {
        private const val FILE_FAKE_REPO_LIST = "fake_repo_list.json"
    }
}
