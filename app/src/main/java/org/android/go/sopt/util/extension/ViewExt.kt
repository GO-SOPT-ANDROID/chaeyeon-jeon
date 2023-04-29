package org.android.go.sopt.util.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.util.OnSingleClickListener

/** Register a callback to be invoked when this view is clicked. If this view was clicked within 1 second, the callback will not be invoked. */
inline fun View.setOnSingleClickListener(crossinline onSingleClick: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener { onSingleClick(it) })
}

/** Make a Snackbar to display a message for 1.5 seconds */
fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}
