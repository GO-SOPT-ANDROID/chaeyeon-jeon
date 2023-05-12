package org.android.go.sopt.util

inline fun <reified T : Enum<T>> safeValueOf(type: String?, default: T): T {
    return try {
        java.lang.Enum.valueOf(T::class.java, type ?: return default)
    } catch (e: IllegalArgumentException) {
        default
    }
}
