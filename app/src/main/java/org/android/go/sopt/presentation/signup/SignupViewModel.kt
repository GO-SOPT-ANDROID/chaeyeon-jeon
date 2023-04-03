package org.android.go.sopt.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {
    val id = MutableLiveData("")
    val pwd = MutableLiveData("")
    val name = MutableLiveData("")
    val specialty = MutableLiveData("")
    val mbti = MutableLiveData("")

    fun isValidId(id: String?) = !id.isNullOrBlank() && id.length in 6..10
    fun isValidPwd(pwd: String?) = !pwd.isNullOrBlank() && pwd.length in 8..12
}
