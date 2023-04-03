package org.android.go.sopt.presentation.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.data.User
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.setOnSingleClickListener
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.safeValueOf
import org.android.go.sopt.util.type.MBTI

@AndroidEntryPoint
class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initLayout()
        initSignupCompleteBtnClickListener()
    }

    private fun initLayout() {
        binding.layoutSignup.setOnSingleClickListener { hideKeyboard() }
    }

    private fun initSignupCompleteBtnClickListener() {
        binding.btnSignupSignupComplete.setOnSingleClickListener {
            with(viewModel) {
                if (isValidId(id.value) && isValidPwd(pwd.value)) {
                    Intent(this@SignupActivity, LoginActivity::class.java).apply {
                        val user = User(id.value!!, pwd.value!!, name.value, specialty.value, safeValueOf<MBTI>(mbti.value))
                        this.putExtra("user", user)
                        setResult(Activity.RESULT_OK, this)
                    }
                    finish()
                    return@setOnSingleClickListener
                }
            }
            showSnackbar(binding.root, getString(R.string.wrong_input_msg))
        }
    }
}
