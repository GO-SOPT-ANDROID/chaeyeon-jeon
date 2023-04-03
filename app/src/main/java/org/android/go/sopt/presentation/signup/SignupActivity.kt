package org.android.go.sopt.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initLayout()
    }

    private fun initLayout() {
        binding.layoutSignup.setOnSingleClickListener { hideKeyboard() }
    }
}
