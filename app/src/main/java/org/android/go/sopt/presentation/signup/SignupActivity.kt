package org.android.go.sopt.presentation.signup

import android.app.Activity
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.presentation.signup.SignupViewModel.Companion.CODE_INVALID_ID
import org.android.go.sopt.presentation.signup.SignupViewModel.Companion.CODE_INVALID_PWD
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.state.RemoteUiState.Error
import org.android.go.sopt.util.state.RemoteUiState.Failure
import org.android.go.sopt.util.state.RemoteUiState.Success

@AndroidEntryPoint
class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        setupSignupState()
    }

    private fun setupSignupState() {
        viewModel.signupState.observe(this) { state ->
            when (state) {
                is Success -> intentToLogin()
                is Failure -> {
                    when (state.code) {
                        CODE_INVALID_ID -> showSnackbar(
                            binding.root,
                            getString(R.string.signup_invalid_id_msg),
                        )

                        CODE_INVALID_PWD -> showSnackbar(
                            binding.root,
                            getString(R.string.signup_invalid_pwd_msg),
                        )
                    }
                }

                is Error -> showSnackbar(binding.root, getString(R.string.server_error_msg))
            }
        }
    }

    private fun intentToLogin() {
        Intent(this, LoginActivity::class.java).apply {
            this.putExtra(EXTRA_USER, viewModel.getUser())
            setResult(Activity.RESULT_OK, this)
            if (!isFinishing) finish()
        }
    }
}
