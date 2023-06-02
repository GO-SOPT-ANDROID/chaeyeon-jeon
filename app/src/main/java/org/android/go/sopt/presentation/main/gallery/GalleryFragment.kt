package org.android.go.sopt.presentation.main.gallery

import android.os.Bundle
import android.view.View
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.util.binding.BindingFragment

class GalleryFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {
    private var _imageAdapter: ImageAdapter? = null
    private val imageAdapter
        get() = requireNotNull(_imageAdapter) { getString(R.string.adapter_not_initialized_error_msg) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initImageAdapter()
    }

    private fun initImageAdapter() {
        _imageAdapter = ImageAdapter()
        binding.vpGallery.adapter = imageAdapter
        imageAdapter.submitList(
            listOf(
                R.drawable.img_main_profile,
                R.drawable.img_main_profile,
                R.drawable.img_main_profile,
            ),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _imageAdapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = GalleryFragment()
    }
}
