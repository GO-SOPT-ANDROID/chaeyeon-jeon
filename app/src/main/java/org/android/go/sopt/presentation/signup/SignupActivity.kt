package org.android.go.sopt.presentation.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.dialog.LoadingDialogFragment
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.presentation.login.LoginActivity.Companion.TAG_LOADING_DIALOG
import org.android.go.sopt.presentation.signup.SignupViewModel.Companion.CODE_DUPLICATED_INFO
import org.android.go.sopt.presentation.signup.SignupViewModel.Companion.CODE_INVALID_INPUT
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.state.RemoteUiState.Error
import org.android.go.sopt.util.state.RemoteUiState.Failure
import org.android.go.sopt.util.state.RemoteUiState.Loading
import org.android.go.sopt.util.state.RemoteUiState.Success

@AndroidEntryPoint
class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel by viewModels<SignupViewModel>()

    private val loadingDialog: LoadingDialogFragment by lazy { LoadingDialogFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        setupSignupState()
    }

    private fun setupSignupState() {
        viewModel.signupState.observe(this) { state ->
            when (state) {
                is Loading -> loadingDialog.show(supportFragmentManager, TAG_LOADING_DIALOG)
                is Success -> {
                    loadingDialog.dismiss()
                    navigateToLoginScreen()
                }

                is Failure -> {
                    loadingDialog.dismiss()
                    when (state.code) {
                        CODE_INVALID_INPUT -> showSnackbar(
                            binding.root,
                            getString(R.string.wrong_input_msg),
                        )

                        CODE_DUPLICATED_INFO -> showSnackbar(
                            binding.root,
                            getString(R.string.signup_duplicated_info_msg),
                        )
                    }
                }

                is Error -> {
                    loadingDialog.dismiss()
                    showSnackbar(binding.root, getString(R.string.unknown_error_msg))
                }
            }
        }
    }

    private fun navigateToLoginScreen() {
        Intent(this, LoginActivity::class.java).apply {
            setResult(Activity.RESULT_OK, this)
            if (!isFinishing) finish()
        }
    }
}
