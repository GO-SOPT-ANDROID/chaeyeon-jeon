package org.android.go.sopt.domain.repository

import okhttp3.MultipartBody

interface ImageRepository {
    suspend fun uploadImage(file: MultipartBody.Part): Result<Unit>
}
