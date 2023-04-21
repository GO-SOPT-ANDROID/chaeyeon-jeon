package org.android.go.sopt.data.source

import com.google.gson.Gson
import org.android.go.sopt.data.entity.MockRepoDto
import org.android.go.sopt.util.AssetLoader
import javax.inject.Inject

class RepoDataSource @Inject constructor(
    private val assetLoader: AssetLoader,
) {
    private val gson = Gson()

    fun getRepoList(): Array<MockRepoDto> {
        return assetLoader.getJsonString("fake_repo_list.json")?.let { jsonString ->
            gson.fromJson(jsonString, Array<MockRepoDto>::class.java)
        } ?: emptyArray()
    }
}
