package org.android.go.sopt.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.setOnSingleClickListener
import org.android.go.sopt.util.extension.showToast

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initLogoutAndLeaveBtnClickListener()
    }

    private fun initLogoutAndLeaveBtnClickListener() {
        binding.btnMainLogoutAndLeave.setOnSingleClickListener {
            viewModel.clearLocalPref()
            showToast(getString(R.string.main_logout_and_leave_msg))
            Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(this)
            }
        }
    }
}
