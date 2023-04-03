package org.android.go.sopt.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.android.go.sopt.data.User
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private lateinit var savedUser: User

    val id = MutableLiveData("")
    val pwd = MutableLiveData("")

    fun setSavedUser(savedUser: User) {
        this.savedUser = savedUser
    }

    fun isValidInput() = !id.value.isNullOrBlank() && id.value == savedUser.id && !pwd.value.isNullOrBlank() && pwd.value == savedUser.pwd
}
