package org.android.go.sopt.presentation.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.presentation.login.LoginActivity.Companion.EXTRA_SIGNUP_USER
import org.android.go.sopt.presentation.signup.SignupViewModel.Companion.INVALID_ID_CODE
import org.android.go.sopt.presentation.signup.SignupViewModel.Companion.INVALID_PWD_CODE
import org.android.go.sopt.util.UiState.Failure
import org.android.go.sopt.util.UiState.Success
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.setOnSingleClickListener
import org.android.go.sopt.util.extension.showSnackbar

@AndroidEntryPoint
class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initLayout()
        setupSignupState()
    }

    private fun initLayout() {
        binding.layoutSignup.setOnSingleClickListener { hideKeyboard() }
    }

    private fun setupSignupState() {
        viewModel.signupState.observe(this) { state ->
            when (state) {
                is Success -> {
                    Intent(this, LoginActivity::class.java).apply {
                        this.putExtra(EXTRA_SIGNUP_USER, viewModel.getUser())
                        setResult(Activity.RESULT_OK, this)
                        finish()
                    }
                }
                is Failure -> {
                    when (state.code) {
                        INVALID_ID_CODE -> showSnackbar(
                            binding.root,
                            getString(R.string.signup_invalid_id_msg),
                        )
                        INVALID_PWD_CODE -> showSnackbar(
                            binding.root,
                            getString(R.string.signup_invalid_pwd_msg),
                        )
                    }
                }
            }
        }
    }
}
