package org.android.go.sopt.util.binding

import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import org.android.go.sopt.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setRoundedCornersImage")
    fun ImageView.setRoundedCornersImage(img: String?) {
        load(img) {
            placeholder(R.drawable.ic_loading)
            error(R.drawable.ic_image_not_supported)
            fallback(R.drawable.ic_image_not_supported)
            transformations(RoundedCornersTransformation(50f))
        }
    }

    @JvmStatic
    @BindingAdapter("setCircleImage")
    fun ImageView.setCircleImage(img: String?) {
        load(img) {
            placeholder(R.drawable.ic_loading)
            error(R.drawable.ic_image_not_supported)
            fallback(R.drawable.ic_image_not_supported)
            transformations(RoundedCornersTransformation(1000f))
        }
    }

    @JvmStatic
    @BindingAdapter("setDrawableTint")
    fun EditText.setDrawableTint(isValid: Boolean) {
        if (isValid) {
            TextViewCompat.setCompoundDrawableTintList(
                this,
                getColorStateList(context, R.color.transparent),
            )
            return
        }
        TextViewCompat.setCompoundDrawableTintList(
            this,
            getColorStateList(context, R.color.coral_700),
        )
    }
}
