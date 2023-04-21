package org.android.go.sopt.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import org.android.go.sopt.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setCircleImage")
    fun ImageView.setCircleImage(img: String?) {
        load(img) {
            // TODO : placeholder & load error 이미지 추가
            placeholder(R.drawable.img_main_profile)
            transformations(RoundedCornersTransformation(50f))
        }
    }
}
