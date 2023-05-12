package org.android.go.sopt.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.data.entity.remote.request.RequestPostSignUpDto
import org.android.go.sopt.data.entity.local.User
import org.android.go.sopt.domain.repository.AuthRepository
import org.android.go.sopt.util.safeValueOf
import org.android.go.sopt.util.state.RemoteUiState
import org.android.go.sopt.util.state.RemoteUiState.Error
import org.android.go.sopt.util.state.RemoteUiState.Failure
import org.android.go.sopt.util.state.RemoteUiState.Success
import org.android.go.sopt.util.type.MBTI
import org.android.go.sopt.util.type.MBTI.NONE
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _signupState = MutableLiveData<RemoteUiState>()
    val signupState: LiveData<RemoteUiState>
        get() = _signupState

    val _id = MutableLiveData("")
    val id: String
        get() = requireNotNull(_id.value).trim()

    val _pwd = MutableLiveData("")
    val pwd: String
        get() = requireNotNull(_pwd.value).trim()

    val _name = MutableLiveData("")
    val name: String
        get() = _name.value?.trim() ?: ""

    val _specialty = MutableLiveData("")
    val specialty: String
        get() = _specialty.value?.trim() ?: ""

    val _mbti = MutableLiveData("")
    val mbti: MBTI?
        get() = safeValueOf(_mbti.value?.trim()?.uppercase(), NONE)

    private fun isValidId(id: String?) =
        !id.isNullOrBlank() && id.length in MIN_ID_LENGTH..MAX_ID_LENGTH

    private fun isValidPwd(pwd: String?) =
        !pwd.isNullOrBlank() && pwd.length in MIN_PWD_LENGTH..MAX_PWD_LENGTH

    fun signup() {
        if (!isValidId(_id.value)) {
            _signupState.value = Failure(CODE_INVALID_ID)
            return
        }
        if (!isValidPwd(_pwd.value)) {
            _signupState.value = Failure(CODE_INVALID_PWD)
            return
        }

        val requestPostSignUpDto = RequestPostSignUpDto(
            id = id,
            password = pwd,
            name = name,
            skill = specialty,
        )
        viewModelScope.launch {
            authRepository.postSignup(requestPostSignUpDto)
                .onSuccess { response ->
                    authRepository.setSignedUpUser(getUser())
                    _signupState.value = Success
                    Timber.d("$response")
                }
                .onFailure { t ->
                    if (t is HttpException) {
                        Timber.e("${t.code()} : ${t.message()}")
                        _signupState.value = Error
                    }
                }
        }
    }

    fun getUser(): User {
        return User(
            id = id,
            pwd = pwd,
            name = name,
            specialty = specialty,
            mbti = mbti,
        )
    }

    companion object {
        const val MIN_ID_LENGTH = 6
        const val MAX_ID_LENGTH = 10
        const val MIN_PWD_LENGTH = 8
        const val MAX_PWD_LENGTH = 12

        const val CODE_INVALID_ID = 100
        const val CODE_INVALID_PWD = 101
    }
}
