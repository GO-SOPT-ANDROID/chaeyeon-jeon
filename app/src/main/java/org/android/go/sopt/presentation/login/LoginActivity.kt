package org.android.go.sopt.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.main.MainActivity
import org.android.go.sopt.presentation.signup.SignupActivity
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.* // ktlint-disable no-wildcard-imports

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var signupResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initLayout()
        initLoginBtnClickListener()
        initSignupBtnClickListener()
    }

    private fun initLayout() {
        binding.layoutLogin.setOnSingleClickListener { hideKeyboard() }
    }

    private fun initLoginBtnClickListener() {
        binding.btnLoginLogin.setOnSingleClickListener {
            if (viewModel.isValidInput()) {
                showToast(getString(R.string.login_login_success_msg))
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                return@setOnSingleClickListener
            }
            showSnackbar(binding.root, getString(R.string.wrong_input_msg))
        }
    }

    private fun initSignupBtnClickListener() {
        signupResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val resultData = result.data ?: return@registerForActivityResult
                    resultData.getCompatibleParcelableExtra<User>(EXTRA_SIGNUP_USER)?.let { user ->
                        viewModel.setSavedUser(user)
                        showSnackbar(binding.root, getString(R.string.login_signup_success_msg))
                    }
                }
            }

        binding.btnLoginSignup.setOnSingleClickListener {
            signupResultLauncher.launch(Intent(this, SignupActivity::class.java))
        }
    }

    companion object {
        const val EXTRA_SIGNUP_USER = "SIGNUP_USER"
    }
}
