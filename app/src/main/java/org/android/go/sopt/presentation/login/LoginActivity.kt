package org.android.go.sopt.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.presentation.signup.SignupActivity
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.setOnSingleClickListener
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.extension.showToast
import org.android.go.sopt.util.state.RemoteUiState.Error
import org.android.go.sopt.util.state.RemoteUiState.Failure
import org.android.go.sopt.util.state.RemoteUiState.Success

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initSignupBtnClickListener()
        setupLoginState()
    }

    private fun initSignupBtnClickListener() {
        val signupResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    showSnackbar(binding.root, getString(R.string.login_signup_success_msg))
                }
            }

        binding.btnLoginSignup.setOnSingleClickListener {
            signupResultLauncher.launch(Intent(this, SignupActivity::class.java))
        }
    }

    private fun setupLoginState() {
        viewModel.loginState.observe(this) { state ->
            when (state) {
                is Success -> navigateToMainScreen()
                is Failure -> showSnackbar(binding.root, getString(R.string.wrong_input_msg))
                is Error -> showSnackbar(binding.root, getString(R.string.unknown_error_msg))
            }
        }
    }

    private fun navigateToMainScreen() {
        showToast(getString(R.string.login_login_success_msg))
        Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }
}
