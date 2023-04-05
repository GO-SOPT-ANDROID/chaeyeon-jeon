package org.android.go.sopt.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.util.binding.BindingActivity

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUserData()
    }

    private fun getUserData() {
        viewModel.getSignedUpUser().also { user ->
            with(binding) {
                tvMainName.text = getString(R.string.main_name, user.name)
                tvMainSpecialty.text = getString(R.string.main_specialty, user.specialty)
                tvMainMbti.text = getString(R.string.main_mbti, user.mbti)
            }
        }
    }
}
