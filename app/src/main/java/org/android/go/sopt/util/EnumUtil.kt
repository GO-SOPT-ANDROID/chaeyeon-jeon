package org.android.go.sopt.util

inline fun <reified T : Enum<T>> safeValueOf(type: String?): T? {
    return try {
        java.lang.Enum.valueOf(T::class.java, type ?: return null)
    } catch (e: IllegalArgumentException) {
        null
    }
}
