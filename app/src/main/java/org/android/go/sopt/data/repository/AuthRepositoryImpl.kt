package org.android.go.sopt.data.repository

import org.android.go.sopt.data.entity.remote.request.RequestPostSignInDto
import org.android.go.sopt.data.entity.remote.request.RequestPostSignUpDto
import org.android.go.sopt.data.entity.remote.response.ResponsePostSignInDto
import org.android.go.sopt.data.entity.remote.response.ResponsePostSignUpDto
import org.android.go.sopt.data.source.local.SharedPrefDataSource
import org.android.go.sopt.data.source.remote.AuthDataSource
import org.android.go.sopt.domain.model.User
import org.android.go.sopt.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val sharedPrefDataSource: SharedPrefDataSource,
) : AuthRepository {
    override suspend fun postSignup(requestPostSignUpDto: RequestPostSignUpDto): Result<ResponsePostSignUpDto?> =
        runCatching {
            authDataSource.postSignup(requestPostSignUpDto).data
        }

    override suspend fun postSignin(requestPostSignInDto: RequestPostSignInDto): Result<ResponsePostSignInDto?> =
        runCatching {
            authDataSource.postSignIn(requestPostSignInDto).data
        }

    override fun setAutoLogin(isAutoLogin: Boolean) {
        sharedPrefDataSource.isAutoLogin = isAutoLogin
    }

    override fun getAutoLogin(): Boolean = sharedPrefDataSource.isAutoLogin

    override fun setSignedUpUser(user: User) {
        sharedPrefDataSource.signedUpUser = user
    }

    override fun getSignedUpUser(): User? = sharedPrefDataSource.signedUpUser

    override fun clearLocalPref() {
        sharedPrefDataSource.clearLocalPref()
    }
}
