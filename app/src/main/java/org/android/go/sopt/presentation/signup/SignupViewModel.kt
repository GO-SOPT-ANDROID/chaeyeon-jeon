package org.android.go.sopt.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class SignupViewModel : ViewModel() {
    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val name = MutableLiveData("")
    val specialty = MutableLiveData("")
    val mbti = MutableLiveData("")
}
