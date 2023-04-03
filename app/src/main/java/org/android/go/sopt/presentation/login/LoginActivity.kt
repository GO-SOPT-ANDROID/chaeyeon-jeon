package org.android.go.sopt.presentation.login

import android.content.Intent
import android.os.Bundle
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.signup.SignupActivity
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.setOnSingleClickListener

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        initLoginBtnClickListener()
    }

    private fun initLayout() {
        binding.layoutLogin.setOnSingleClickListener { hideKeyboard() }
    }

    private fun initLoginBtnClickListener() {
        binding.btnLoginLogin.setOnSingleClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
