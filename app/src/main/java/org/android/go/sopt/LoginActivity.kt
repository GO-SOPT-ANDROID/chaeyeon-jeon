package org.android.go.sopt

import android.content.Intent
import android.os.Bundle
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.setOnSingleClickListener

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setLoginBtnClickListener()
    }

    private fun setLoginBtnClickListener() {
        binding.btnLoginLogin.setOnSingleClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
