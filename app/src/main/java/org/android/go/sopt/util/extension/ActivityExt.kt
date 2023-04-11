package org.android.go.sopt.util.extension

import android.app.Activity
import android.view.View

/** Hide keyboard from activity window */
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
