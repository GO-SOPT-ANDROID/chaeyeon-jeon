package org.android.go.sopt.presentation.login

import android.app.Activity
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.data.entity.User
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.presentation.signup.SignupActivity
import org.android.go.sopt.util.UiState.Failure
import org.android.go.sopt.util.UiState.Success
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.* // ktlint-disable no-wildcard-imports

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initLayout()
        initSignupBtnClickListener()
        setupLoginState()
    }

    private fun initLayout() {
        binding.layoutLogin.setOnSingleClickListener { hideKeyboard() }
    }

    private fun initSignupBtnClickListener() {
        val signupResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val resultData = result.data ?: return@registerForActivityResult
                    resultData.getCompatibleParcelableExtra<User>(EXTRA_USER)?.let { user ->
                        viewModel.setSavedUser(user)
                        showSnackbar(binding.root, getString(R.string.login_signup_success_msg))
                    }
                }
            }

        binding.btnLoginSignup.setOnSingleClickListener {
            signupResultLauncher.launch(Intent(this, SignupActivity::class.java))
        }
    }

    private fun setupLoginState() {
        viewModel.loginState.observe(this) { state ->
            when (state) {
                is Success -> {
                    showToast(getString(R.string.login_login_success_msg))
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is Failure -> showSnackbar(binding.root, getString(R.string.wrong_input_msg))
            }
        }
    }
}
