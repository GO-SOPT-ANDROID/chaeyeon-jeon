package org.android.go.sopt.presentation.main

import android.content.Intent.EXTRA_USER
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.data.entity.User
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.extension.getCompatibleParcelableExtra

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUserData()
    }

    private fun getUserData() {
        if (intent.hasExtra(EXTRA_USER)) {
            intent.getCompatibleParcelableExtra<User>(EXTRA_USER)?.let { user ->
                with(binding) {
                    tvMainName.text = getString(R.string.main_name, user.name)
                    tvMainSpecialty.text = getString(R.string.main_specialty, user.specialty)
                    tvMainMbti.text = getString(R.string.main_mbti, user.mbti)
                }
            }
        }
    }
}
