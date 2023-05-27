package org.android.go.sopt.presentation.main.gallery

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.util.ContentUriRequestBody
import org.android.go.sopt.util.binding.BindingFragment
import org.android.go.sopt.util.extension.setOnSingleClickListener
import org.android.go.sopt.util.extension.showSnackbar
import org.android.go.sopt.util.state.RemoteUiState.Error
import org.android.go.sopt.util.state.RemoteUiState.Failure
import org.android.go.sopt.util.state.RemoteUiState.Success

@AndroidEntryPoint
class GalleryFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {
    private val viewModel by viewModels<GalleryViewModel>()
    private val launcher =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(maxItems = 3)) { imageUriList: List<Uri> ->
            with(binding) {
                if (imageUriList.size !in 1..3) {
                    requireContext().showSnackbar(binding.root, "최소 1개 최대 3개의 이미지를 선택해주세요.")
                    return@registerForActivityResult
                }
                for (i in imageUriList.indices) {
                    (layoutGallery.getChildAt(i) as ImageView).load(imageUriList[i])
                }
                viewModel.setImageFile(ContentUriRequestBody(requireContext(), imageUriList[0]))
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initSelectBtnClickListener()
        setupPostImageState()
    }

    private fun initSelectBtnClickListener() {
        binding.btnGallerySelect.setOnSingleClickListener {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }

    private fun setupPostImageState() {
        viewModel.postImageState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Success -> {
                    requireContext().showSnackbar(binding.root, "이미지가 전송되었습니다.")
                }

                is Failure -> {
                    requireContext().showSnackbar(binding.root, "이미지 전송에 실패하였습니다.")
                }

                is Error -> {
                    requireContext().showSnackbar(binding.root, "서버에 연결할 수 없습니다.")
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GalleryFragment()
    }
}
