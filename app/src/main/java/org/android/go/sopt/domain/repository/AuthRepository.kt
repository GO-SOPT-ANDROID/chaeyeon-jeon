package org.android.go.sopt.domain.repository

import org.android.go.sopt.data.entity.remote.request.RequestPostSignUpDto
import org.android.go.sopt.data.entity.remote.response.ResponsePostSignUpDto
import org.android.go.sopt.data.entity.local.User

interface AuthRepository {
    suspend fun postSignup(requestPostSignUpDto: RequestPostSignUpDto): Result<ResponsePostSignUpDto?>

    fun setAutoLogin(isAutoLogin: Boolean)

    fun getAutoLogin(): Boolean

    fun setSignedUpUser(user: User)

    fun getSignedUpUser(): User?

    fun clearLocalPref()
}
