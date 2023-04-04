package org.android.go.sopt.util.extension

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Parcelable

/** Retrieve extended data from the intent and support app compatibility */
inline fun <reified T : Parcelable> Intent.getCompatibleParcelableExtra(key: String): T? = when {
    SDK_INT >= TIRAMISU -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T // ktlint-disable annotation
}
