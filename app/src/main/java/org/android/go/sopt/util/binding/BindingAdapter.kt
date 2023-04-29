package org.android.go.sopt.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setRoundedCornersImage")
    fun ImageView.setRoundedCornersImage(img: String?) {
        load(img) {
            // TODO: placeholder & load error 이미지 추가
            transformations(RoundedCornersTransformation(50f))
        }
    }
}
