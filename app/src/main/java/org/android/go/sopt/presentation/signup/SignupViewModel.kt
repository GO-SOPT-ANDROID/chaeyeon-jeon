package org.android.go.sopt.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.android.go.sopt.data.entity.remote.request.RequestPostSignUpDto
import org.android.go.sopt.domain.repository.AuthRepository
import org.android.go.sopt.util.state.RemoteUiState
import org.android.go.sopt.util.state.RemoteUiState.Error
import org.android.go.sopt.util.state.RemoteUiState.Failure
import org.android.go.sopt.util.state.RemoteUiState.Success
import retrofit2.HttpException
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _signupState = MutableLiveData<RemoteUiState>()
    val signupState: LiveData<RemoteUiState>
        get() = _signupState

    val _id = MutableLiveData("")
    private val id: String
        get() = _id.value?.trim() ?: ""

    val _pwd = MutableLiveData("")
    private val pwd: String
        get() = _pwd.value?.trim() ?: ""

    val _name = MutableLiveData("")
    private val name: String
        get() = _name.value?.trim() ?: ""

    val _specialty = MutableLiveData("")
    private val specialty: String
        get() = _specialty.value?.trim() ?: ""

    val isValidId: LiveData<Boolean> = _id.map { id -> checkId(id) }
    val isValidPwd: LiveData<Boolean> = _pwd.map { pwd -> checkPwd(pwd) }

    private fun checkId(id: String): Boolean {
        return id.isEmpty() || Pattern.matches(REGEX_ID_PATTERN, id)
    }

    private fun checkPwd(pwd: String): Boolean {
        return pwd.isEmpty() || Pattern.matches(REGEX_PWD_PATTERN, pwd)
    }

    fun signup() {
        val requestPostSignUpDto = RequestPostSignUpDto(
            id = id,
            password = pwd,
            name = name,
            skill = specialty,
        )

        viewModelScope.launch {
            authRepository.postSignup(requestPostSignUpDto)
                .onSuccess { response ->
                    _signupState.value = Success
                    Timber.d("POST SIGNUP SUCCESS : $response")
                }
                .onFailure { t ->
                    if (t is HttpException) {
                        when (t.code()) {
                            CODE_INVALID_INPUT -> _signupState.value = Failure(CODE_INVALID_INPUT)
                            CODE_DUPLICATED_INFO ->
                                _signupState.value =
                                    Failure(CODE_DUPLICATED_INFO)

                            else -> _signupState.value = Error
                        }
                        Timber.e("POST SIGNUP FAIL ${t.code()} : ${t.message()}")
                    }
                }
        }
    }

    companion object {
        private const val MIN_ID_LENGTH = 6
        const val MAX_ID_LENGTH = 10
        private const val MIN_PWD_LENGTH = 8
        const val MAX_PWD_LENGTH = 12

        const val CODE_INVALID_INPUT = 400
        const val CODE_DUPLICATED_INFO = 409

        private const val REGEX_ID_PATTERN =
            """^(?=.*[a-zA-Z])(?=.*\d).{$MIN_ID_LENGTH,$MAX_ID_LENGTH}$"""
        private const val REGEX_PWD_PATTERN =
            """^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~!@#$%^&*()?]).{$MIN_PWD_LENGTH,$MAX_PWD_LENGTH}$"""
    }
}
