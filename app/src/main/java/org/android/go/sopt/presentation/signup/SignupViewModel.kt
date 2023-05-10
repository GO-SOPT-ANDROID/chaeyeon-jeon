package org.android.go.sopt.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.domain.model.User
import org.android.go.sopt.domain.repository.AuthRepository
import org.android.go.sopt.util.UiState
import org.android.go.sopt.util.UiState.Failure
import org.android.go.sopt.util.UiState.Success
import org.android.go.sopt.util.safeValueOf
import org.android.go.sopt.util.type.MBTI.NONE
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _signupState = MutableLiveData<UiState>()
    val signupState: LiveData<UiState>
        get() = _signupState

    val id = MutableLiveData("")
    val pwd = MutableLiveData("")
    val name = MutableLiveData("")
    val specialty = MutableLiveData("")
    val mbti = MutableLiveData("")

    private fun isValidId(id: String?) =
        !id.isNullOrBlank() && id.length in MIN_ID_LENGTH..MAX_ID_LENGTH

    private fun isValidPwd(pwd: String?) =
        !pwd.isNullOrBlank() && pwd.length in MIN_PWD_LENGTH..MAX_PWD_LENGTH

    fun signup() {
        if (!isValidId(id.value)) {
            _signupState.value = Failure(CODE_INVALID_ID)
            return
        }
        if (!isValidPwd(pwd.value)) {
            _signupState.value = Failure(CODE_INVALID_PWD)
            return
        }
        authRepository.setSignedUpUser(getUser())
        _signupState.value = Success
    }

    fun getUser(): User {
        return User(
            requireNotNull(id.value).trim(),
            requireNotNull(pwd.value).trim(),
            name.value?.trim(),
            specialty.value?.trim(),
            safeValueOf(mbti.value?.trim()?.uppercase(), NONE),
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
