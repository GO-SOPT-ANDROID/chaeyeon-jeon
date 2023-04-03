package org.android.go.sopt.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LoginViewModel : ViewModel() {
    val id = MutableLiveData("")
    val password = MutableLiveData("")
}
