package org.android.go.sopt.presentation.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentProfileBinding
import org.android.go.sopt.presentation.login.LoginActivity
import org.android.go.sopt.util.binding.BindingFragment
import org.android.go.sopt.util.extension.setOnSingleClickListener
import org.android.go.sopt.util.extension.showToast

@AndroidEntryPoint
class ProfileFragment : BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initLogoutAndLeaveBtnClickListener()
    }

    private fun initLogoutAndLeaveBtnClickListener() {
        binding.btnProfileLogoutAndLeave.setOnSingleClickListener {
            // TODO: show Logout And Leave Dialog
            viewModel.clearLocalPref()
            requireContext().showToast(getString(R.string.profile_logout_and_leave_msg))
            intentToLogin()
        }
    }

    private fun intentToLogin() {
        Intent(activity, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}
