package org.android.go.sopt.data.service

import org.android.go.sopt.data.entity.remote.request.RequestPostSignInDto
import org.android.go.sopt.data.entity.remote.request.RequestPostSignUpDto
import org.android.go.sopt.data.entity.remote.response.ResponsePostSignInDto
import org.android.go.sopt.data.entity.remote.response.ResponsePostSignUpDto
import org.android.go.sopt.data.entity.remote.response.wrapper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("sign-up")
    suspend fun postSignUp(
        @Body requestPostSignUpDto: RequestPostSignUpDto,
    ): BaseResponse<ResponsePostSignUpDto>

    @POST("sign-in")
    suspend fun postSignIn(
        @Body requestPostSignInDto: RequestPostSignInDto,
    ): BaseResponse<ResponsePostSignInDto>
}
