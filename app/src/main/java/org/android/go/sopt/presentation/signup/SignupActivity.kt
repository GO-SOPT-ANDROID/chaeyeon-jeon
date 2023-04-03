package org.android.go.sopt.presentation.signup

import android.os.Bundle
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.hideKeyboard
import org.android.go.sopt.util.extension.setOnSingleClickListener

class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
    }

    private fun initLayout() {
        binding.layoutSignup.setOnSingleClickListener { hideKeyboard() }
    }
}
