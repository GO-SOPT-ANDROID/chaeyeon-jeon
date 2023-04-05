package org.android.go.sopt.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.data.entity.User
import org.android.go.sopt.util.UiState
import org.android.go.sopt.util.UiState.Failure
import org.android.go.sopt.util.UiState.Success
import org.android.go.sopt.util.safeValueOf
import org.android.go.sopt.util.type.MBTI
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {
    private val _signupState = MutableLiveData<UiState>()
    val signupState: LiveData<UiState>
        get() = _signupState

    val id = MutableLiveData("")
    val pwd = MutableLiveData("")
    val name = MutableLiveData("")
    val specialty = MutableLiveData("")
    val mbti = MutableLiveData("")

    private fun isValidId(id: String?) = !id.isNullOrBlank() && id.length in 6..10

    private fun isValidPwd(pwd: String?) = !pwd.isNullOrBlank() && pwd.length in 8..12

    fun signup() {
        if (!isValidId(id.value)) {
            _signupState.value = Failure(INVALID_ID_CODE)
            return
        }
        if (!isValidPwd(pwd.value)) {
            _signupState.value = Failure(INVALID_PWD_CODE)
            return
        }
        _signupState.value = Success
    }

    fun getUser(): User {
        return User(
            id.value!!.trim(),
            pwd.value!!.trim(),
            name.value?.trim(),
            specialty.value?.trim(),
            safeValueOf<MBTI>(mbti.value?.trim()?.uppercase()),
        )
    }

    companion object {
        const val INVALID_ID_CODE = 100
        const val INVALID_PWD_CODE = 101
    }
}
