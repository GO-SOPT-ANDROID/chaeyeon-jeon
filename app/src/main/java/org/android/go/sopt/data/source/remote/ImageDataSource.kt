package org.android.go.sopt.data.source.remote

import okhttp3.MultipartBody
import org.android.go.sopt.data.service.ImageService
import javax.inject.Inject

class ImageDataSource @Inject constructor(
    private val imageService: ImageService,
) {
    suspend fun uploadImage(file: MultipartBody.Part) = imageService.uploadImage(file)
}
