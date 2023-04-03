package org.android.go.sopt.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.signup.SignupActivity
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.setOnSingleClickListener
import org.android.go.sopt.util.extension.showSnackbar

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

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
            showSnackbar(binding.root, "login button clicked")
        }
    }

    private fun initSignupBtnClickListener() {
        binding.btnLoginSignup.setOnSingleClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
