package org.android.go.sopt.data.repository

import org.android.go.sopt.data.entity.remote.request.RequestPostSignUpDto
import org.android.go.sopt.data.entity.remote.response.ResponsePostSignUpDto
import org.android.go.sopt.data.source.AuthDataSource
import org.android.go.sopt.data.source.LocalPrefDataSource
import org.android.go.sopt.domain.model.User
import org.android.go.sopt.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localPrefDataSource: LocalPrefDataSource,
) : AuthRepository {
    override suspend fun postSignup(requestPostSignUpDto: RequestPostSignUpDto): Result<ResponsePostSignUpDto> =
        runCatching {
            authDataSource.postSignup(requestPostSignUpDto).data
        }

    override fun setAutoLogin(isAutoLogin: Boolean) {
        localPrefDataSource.isAutoLogin = isAutoLogin
    }

    override fun getAutoLogin(): Boolean = localPrefDataSource.isAutoLogin

    override fun setSignedUpUser(user: User) {
        localPrefDataSource.signedUpUser = user
    }

    override fun getSignedUpUser(): User? = localPrefDataSource.signedUpUser

    override fun clearLocalPref() {
        localPrefDataSource.clearLocalPref()
    }
}
