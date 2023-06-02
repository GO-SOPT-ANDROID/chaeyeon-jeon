package org.android.go.sopt.presentation.main.gallery

import android.os.Bundle
import android.view.View
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.util.binding.BindingFragment

class GalleryFragment : BindingFragment<FragmentGalleryBinding>(R.layout.fragment_gallery) {
    private var imageAdapter: ImageAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initImageAdapter()
    }

    private fun initImageAdapter() {
        imageAdapter = ImageAdapter()
        binding.vpGallery.adapter = imageAdapter
        imageAdapter?.submitList(
            listOf(
                R.drawable.img_main_profile,
                R.drawable.img_main_profile,
                R.drawable.img_main_profile,
            ),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        imageAdapter = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = GalleryFragment()
    }
}
