package org.android.go.sopt.data.repository

import okhttp3.MultipartBody
import org.android.go.sopt.data.source.remote.ImageDataSource
import org.android.go.sopt.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDataSource: ImageDataSource,
) : ImageRepository {
    override suspend fun uploadImage(file: MultipartBody.Part): Result<Unit> = kotlin.runCatching {
        imageDataSource.uploadImage(file)
    }
}
