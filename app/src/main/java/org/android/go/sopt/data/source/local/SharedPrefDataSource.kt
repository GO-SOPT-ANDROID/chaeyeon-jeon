package org.android.go.sopt.data.source.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.android.go.sopt.data.entity.local.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefDataSource @Inject constructor(
    private val prefs: SharedPreferences,
) {
    var isAutoLogin: Boolean
        set(value) = prefs.edit { putBoolean(PREF_IS_AUTO_LOGIN, value) }
        get() = prefs.getBoolean(PREF_IS_AUTO_LOGIN, false)

    var signedUpUser: User?
        set(value) = prefs.edit {
            val user = GsonBuilder().create().toJson(value)
            putString(PREF_SIGNED_UP_USER, user)
        }
        get() {
            val user = prefs.getString(PREF_SIGNED_UP_USER, "")
            return try {
                Gson().fromJson(user, User::class.java)
            } catch (e: Exception) {
                null
            }
        }

    fun clearLocalPref() = prefs.edit { clear() }

    companion object {
        const val PREF_IS_AUTO_LOGIN = "IS_AUTO_LOGIN"
        const val PREF_SIGNED_UP_USER = "SIGNED_UP_USER"
    }
}
